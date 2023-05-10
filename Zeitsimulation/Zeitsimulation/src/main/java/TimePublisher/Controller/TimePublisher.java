package TimePublisher.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/")
public class TimePublisher implements CommandLineRunner {
    private AtomicReference<Double> accelerationFactor = new AtomicReference<>(1.0);
    private LocalDateTime startTime;
    private Duration totalAcceleratedTime = Duration.ZERO;

    @PostMapping("/acceleration")
    public ResponseEntity<String> setAccelerationFactor(@Valid @RequestParam @Min(0) @Max(1000)  Double accelerationFactor) {
        if(accelerationFactor < 0 || accelerationFactor > 1000)
            return ResponseEntity.badRequest().body("Acceleration factor must be between 0 and 1000");
        this.accelerationFactor.set(accelerationFactor);
        return ResponseEntity.ok("Acceleration factor set to " + accelerationFactor);
    }

    @GetMapping("/acceleration")
    public ResponseEntity<Double> getAccelerationFactor() {
        return ResponseEntity.ok(accelerationFactor.get());
    }

    @GetMapping("/")
    public ResponseEntity<String> getTime() {
        var formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        var acceleratedTime = startTime.plus(totalAcceleratedTime)
                                            .minusYears(3)
                                            .plusHours(2);
        return ResponseEntity.ok(String.format("{\"date\": \"%s\", \"time\": \"%s\", \"accelerationFactor\": %.2f}",
                acceleratedTime.format(formatterDate),
                acceleratedTime.format(formatterTime),
                accelerationFactor.get()));
    }




    @Override
    public void run(String... args) throws Exception {
        var brokerUrl = "tcp://159.89.104.105:1883";
        var clientID = "TimePublisher";
        var pubTopic = "fhdo/time";
        var qos = 0;

        var lastPublishedTime = Duration.ZERO;


        final var client = new MqttClient(brokerUrl, clientID, new MemoryPersistence());
        var options = new MqttConnectOptions();
        options.setUserName("fhdo");
        options.setPassword("fhdo".toCharArray());

        options.setCleanSession(true);

        System.out.println("Connecting to broker: " + brokerUrl);
        client.connect(options);


        System.out.println("Connected successfully to broker: " + brokerUrl);

        startTime = LocalDateTime.now();

        while (true) {
            totalAcceleratedTime = totalAcceleratedTime.plusSeconds(1);
            lastPublishedTime = lastPublishedTime.minusSeconds(1);
            var acceleratedTime = startTime.plus(totalAcceleratedTime);

            if(acceleratedTime.isAfter(LocalDateTime.of(2024, 1, 1, 0, 0))) {
                startTime = acceleratedTime = LocalDateTime.of(2023,1,1,0,0);
            }



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
