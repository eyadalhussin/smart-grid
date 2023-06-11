package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.enums.EmergencyLevel;
import org.springframework.stereotype.Service;

@Service
public class EmergencyService {

    private EmergencyLevel emergencyLevel = EmergencyLevel.NONE;

    public EmergencyLevel getEmergencyLevel() {
        return emergencyLevel;
    }

    public void emergencyShutdown() {
        switch (emergencyLevel) {
            case NONE ->
                //No emergency handling necessary
                    System.out.println("Emergency level: NONE");
            case LOW ->
                //TODO: Shutdown non-essential devices
                    System.out.println("Emergency level: LOW");
            case MEDIUM ->
                //TODO: Shutdown all devices
                    System.out.println("Emergency level: MEDIUM");
            case HIGH ->
                //TODO: Complete shutdown some random smart homes to save energy
                    System.out.println("Emergency level: HIGH");
            case CRITICAL ->
                //TODO: Complete shutdown all smart homes to save energy
                    System.out.println("Emergency level: CRITICAL");
        }
        System.out.println("Emergency shutdown");
        emergencyLevel = emergencyLevel.next();
    }


}
