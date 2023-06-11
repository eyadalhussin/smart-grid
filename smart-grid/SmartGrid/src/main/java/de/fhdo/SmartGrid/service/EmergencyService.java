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
        System.out.println("Emergency shutdown");
        emergencyLevel = emergencyLevel.next();
    }


}
