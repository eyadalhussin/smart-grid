package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.enums.EmergencyLevel;
import de.fhdo.SmartGrid.repository.TarifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

@Service
public class TarifService {
    private final EnergyProducerService energyProducerService;
    private final EnergyConsumerService energyConsumerService;
    private final EnergyStorageService energyStorageService;
    private final TimeSimulationService timeSimulationComponent;
    private final EmergencyService emergencyService;
    @Autowired
    public TarifService(TarifRepository tarifRepository, EnergyProducerService energyProducerService, EnergyConsumerService energyConsumerService, EnergyStorageService energyStorageService, TimeSimulationService timeSimulationComponent, EmergencyService emergencyService) {
        this.energyProducerService = energyProducerService;
        this.energyConsumerService = energyConsumerService;
        this.energyStorageService = energyStorageService;
        this.timeSimulationComponent = timeSimulationComponent;
        this.emergencyService = emergencyService;
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

    private PeakTime getCurrentPeakTime() {
        Instant currentTime = timeSimulationComponent.getCurrentTime();

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

    private enum PeakTime {
        BASE,
        PEAK,
        OFFPEAK
    }


}


