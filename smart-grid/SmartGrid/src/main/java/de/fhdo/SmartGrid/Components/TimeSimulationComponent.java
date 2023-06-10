package de.fhdo.SmartGrid.Components;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimeSimulationComponent implements CommandLineRunner {
    private Instant CurrentTime;
    private List<TimeObserver> timeObservers = new ArrayList<>();

    public void registerObserver(TimeObserver observer) {
        timeObservers.add(observer);
    }

    public void unregisterObserver(TimeObserver observer) {
        timeObservers.remove(observer);
    }
    @Override
    public void run(String... args) throws Exception {
        var brokerUrl = "tcp://159.89.104.105:1883";
        var clientID = "WeatherController";
        var topic = "fhdo/time";
        var qos = 0;

        final var client = new MqttClient(brokerUrl, clientID, new MemoryPersistence());
        var options = new MqttConnectOptions();
        options.setUserName("fhdo");
        options.setPassword("fhdo".toCharArray());

        options.setCleanSession(true);

        System.out.println("Connecting to broker: " + brokerUrl);
        client.connect(options);
        System.out.println("Connected successfully to broker: " + brokerUrl);


        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        client.subscribe(topic, (topic1, message) -> {
            try {
                var time = mapper.readValue(message.getPayload(), DateTime.class);
                var localDateTime = LocalDateTime.of(time.date, time.time)
                        .minusYears(3)
                        .withMinute(0)
                        .withSecond(0)
                        .withNano(0);
                CurrentTime = localDateTime.toInstant(ZoneOffset.UTC);

                // Observer benachrichtigen, dass sich die Zeit geändert hat. Wird in einem eigenen Thread ausgeführt, damit der MQTT-Client nicht blockiert wird.
                new Thread( () -> timeObservers.forEach(TimeObserver::timeUpdated)).start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Instant getCurrentTime() {
        return CurrentTime;
    }

    // Klasse zum Empfangen der JSON-Daten
    static class DateTime {
        public LocalDate date;
        public LocalTime time;
        public double accelerationFactor;
    }
}
