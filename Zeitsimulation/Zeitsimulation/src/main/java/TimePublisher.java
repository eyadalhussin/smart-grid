import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class TimePublisher {
    public static void main(String[] args) throws MqttException {
        var brokerUrl = "tcp://159.89.104.105:1883";
        var clientID = "TimePublisher";
        var pubTopic = "fhdo/time";
        var qos = 0;

        var totalAcceleratedTime = Duration.ZERO;
        var lastPublishedTime = Duration.ZERO;
        var accelerationFactor = new AtomicReference<>(1.0);

        var userInputThread = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            System.out.printf("Aktueller Beschleunigungsfaktor: %.2f\n", accelerationFactor.get());
            while(true) {
                try {
                    System.out.print("Geben Sie einen neuen Beschleunigungsfaktor ein: ");
                    var newFactor = sc.nextDouble();
                    sc.nextLine();
                    accelerationFactor.set(newFactor);
                    System.out.printf("Neuer Beschleunigungsfaktor: %.2f\n", accelerationFactor.get());
                } catch (NumberFormatException e) {
                    System.err.println("Ungültige Eingabe! Bitte geben Sie einen gültigen Wert ein.");
                }
            }
        });

        userInputThread.setDaemon(true);


        final var client = new MqttClient(brokerUrl, clientID, new MemoryPersistence());
        var options = new MqttConnectOptions();
        options.setUserName("fhdo");
        options.setPassword("fhdo".toCharArray());

        options.setCleanSession(true);

        System.out.println("Connecting to broker: " + brokerUrl);
        client.connect(options);


        System.out.println("Connected successfully to broker: " + brokerUrl);

        userInputThread.start();
        var startTime = LocalDateTime.now();

        while (true) {
            totalAcceleratedTime = totalAcceleratedTime.plusSeconds(1);
            lastPublishedTime = lastPublishedTime.minusSeconds(1);
            var acceleratedTime = startTime.plus(totalAcceleratedTime);


            var formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            var formatterTime = DateTimeFormatter.ofPattern("HH:mm");

            if(lastPublishedTime.isNegative()) {

                var jsonPayload = String.format("{\"date\": \"%s\", \"time\": \"%s\"}",
                        acceleratedTime.format(formatterDate),
                        acceleratedTime.format(formatterTime));
                var message = new MqttMessage(jsonPayload.getBytes());
                message.setQos(qos);
                client.publish(pubTopic, message);
                lastPublishedTime = Duration.ofSeconds(60);
            }

            try {
                var sleepInterval = (long) (1000 / accelerationFactor.get());
                Thread.sleep(sleepInterval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
