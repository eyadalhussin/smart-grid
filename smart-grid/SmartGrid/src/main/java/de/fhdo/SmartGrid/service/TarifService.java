package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.Observer.WeatherObserver;
import de.fhdo.SmartGrid.enums.EmergencyLevel;
import de.fhdo.SmartGrid.model.Tarif;
import de.fhdo.SmartGrid.repository.TarifRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Optional;

//TODO: This class is a mess, needs refactoring and still needs to be implemented correctly
@Service
public class TarifService implements WeatherObserver {
    private final EnergyProducerService energyProducerService;
    private final SmartHomeService energyConsumerService;
    private final EnergyStorageService energyStorageService;
    private final TimeSimulationService timeSimulationService;
    private final EmergencyService emergencyService;
    private final WeatherService weatherService;
    private final TarifRepository tarifRepository;

    @Autowired
    public TarifService(TarifRepository tarifRepository, EnergyProducerService energyProducerService, SmartHomeService energyConsumerService, EnergyStorageService energyStorageService, TimeSimulationService timeSimulationComponent, EmergencyService emergencyService, WeatherService weatherService) {
        this.energyProducerService = energyProducerService;
        this.energyConsumerService = energyConsumerService;
        this.energyStorageService = energyStorageService;
        this.timeSimulationService = timeSimulationComponent;
        this.emergencyService = emergencyService;
        this.weatherService = weatherService;
        this.tarifRepository = tarifRepository;
    }

    @PostConstruct
    public void init() {
        weatherService.registerObserver(this);
    }

    public double calculateTarif() {
        if(emergencyService.getEmergencyLevel() != EmergencyLevel.NONE) {
            //TODO: emergency handling
            return 0d;
        }
        //Values are all in €/kWh
        double basePrice = 0.15;
        double peakPrice = 0.20;
        double offPeakPrice = 0.10;

        double currentEnergyGeneration = energyProducerService.calculateCurrentEnergyGeneration();
        double currentEnergyConsumption = energyConsumerService.calculateCurrentEnergyConsumption();
        double currentEnergyStorageFillPercentage = energyStorageService.getPercentageFillRate();

        double currentEnergyBalance = currentEnergyGeneration - currentEnergyConsumption;

        if(currentEnergyBalance < 0 && currentEnergyStorageFillPercentage < 0.2) {
            System.out.println("THIS IS AN EMERGENCY!");
            System.out.println("TRYING TO POWER OFF ALL NON-ESSENTIAL DEVICES!");
            emergencyService.emergencyShutdown();
            //TODO: emergency handling
        }

        return 1d;
    }

    public Tarif getCurrentTarif() {
        Optional<Tarif> tarif = tarifRepository.findByActiveTrue();
        return tarif.orElse(null);
    }

    private PeakTime getCurrentPeakTime() {
        Instant currentTime = timeSimulationService.getCurrentTime();

        // Konvertiere das aktuelle 'Instant' in 'LocalTime'
        LocalTime currentLocalTime = currentTime.atZone(ZoneId.systemDefault()).toLocalTime();

        // Definiere die PEAK, OFFPEAK und BASE Zeiten
        LocalTime startPeakTime = LocalTime.of(9, 0);
        LocalTime endPeakTime = LocalTime.of(17, 0);
        LocalTime startOffPeakTime = LocalTime.of(22, 0);
        LocalTime endOffPeakTime = LocalTime.of(6, 0);

        // Überprüfe die aktuelle Zeit und gib den entsprechenden 'PeakTime' Wert zurück
        if (!currentLocalTime.isBefore(startPeakTime) && !currentLocalTime.isAfter(endPeakTime)) {
            return PeakTime.PEAK;
        } else if (!currentLocalTime.isBefore(startOffPeakTime) || !currentLocalTime.isAfter(endOffPeakTime)) {
            return PeakTime.OFFPEAK;
        } else {
            return PeakTime.BASE;
        }
    }

    //Der Tarif muss nur neu berechnet werden, wenn sich das Wetter ändert, da in dieser Simulation sich das Wetter stündlich ändert ist so auch die Zeitbehandlung abgedeckt
    @Override
    public void WeatherUpdated() {
        calculateTarif();
    }

    private enum PeakTime {
        BASE,
        PEAK,
        OFFPEAK
    }


}


