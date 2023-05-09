import com.hivemq.client.mqtt.MqttClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

import static com.hivemq.client.mqtt.MqttGlobalPublishFilter.ALL;
import static java.nio.charset.StandardCharsets.UTF_8;

public class TimePublisher {



    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        
        var brokerUrl = "9f9d9269beaa47d0b0ff7b8e478c13b5.s2.eu.hivemq.cloud";
        var username = "fhswt2";
        var password = "FHDOswt2";

        var totalAcceleratedTime = Duration.ZERO;
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


        final var client = MqttClient.builder()
                .useMqttVersion5()
                .serverHost(brokerUrl)
                .serverPort(8883)
                .sslWithDefaultConfig()
                .buildBlocking();

        client.connectWith()
                .simpleAuth()
                .username(username)
                .password(UTF_8.encode(password))
                .applySimpleAuth()
                .send();

        System.out.println("Connected successfully to broker: " + brokerUrl);

        userInputThread.start();
        var startTime = LocalDateTime.now();

        while (true) {
            totalAcceleratedTime = totalAcceleratedTime.plusSeconds(1);
            var acceleratedTime = startTime.plus(totalAcceleratedTime);

            var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            client.publishWith()
                    .topic("fhdo/time")
                    .payload(UTF_8.encode(acceleratedTime.format(formatter)))
                    .send();

            try {
                var sleepInterval = (long) (1000 / accelerationFactor.get());
                Thread.sleep(sleepInterval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
