package de.fhdo.SmartGrid.service;


import de.fhdo.SmartGrid.model.EnergyStorage;
import de.fhdo.SmartGrid.repository.EnergyStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnergyStorageService {

    private EnergyStorageRepository energyStorageRepository;

    @Autowired
    public EnergyStorageService(EnergyStorageRepository energyStorageRepository) {
        this.energyStorageRepository = energyStorageRepository;
    }

    public List<EnergyStorage> getAllEnergyStorages() {
        return energyStorageRepository.findAll();
    }

    public Optional<EnergyStorage> getEnergyStorageById(Long id) {
        return energyStorageRepository.findById(id);
    }

    public EnergyStorage addEnergyStorage(EnergyStorage energyStorage) {
        return energyStorageRepository.save(energyStorage);
    }

    public EnergyStorage updateEnergyStorage(Long id, EnergyStorage energyStorage) {
        Optional<EnergyStorage> existingEnergyStorage = energyStorageRepository.findById(id);

        if (existingEnergyStorage.isPresent()) {
            EnergyStorage updatedEnergyStorage = existingEnergyStorage.get();
            updatedEnergyStorage.setName(energyStorage.getName());
            updatedEnergyStorage.setCapacity(energyStorage.getCapacity());
            updatedEnergyStorage.setChargeLevel(energyStorage.getChargeLevel());
            return energyStorageRepository.save(updatedEnergyStorage);
        } else {
            return null;
        }
    }

    public void distributeEnergy(double amount) {
        List<EnergyStorage> allEnergyStorages = energyStorageRepository.findAll();

        double amountPerStorage = amount / allEnergyStorages.size();

        for (EnergyStorage storage : allEnergyStorages) {
            double newChargeLevel = storage.getChargeLevel() + amountPerStorage;

            if (newChargeLevel > storage.getCapacity()) {
                storage.setChargeLevel(storage.getCapacity());
            } else {
                storage.setChargeLevel(newChargeLevel);
            }
            energyStorageRepository.save(storage);
        }
    }

    public void deleteEnergyStorage(Long id) {
        energyStorageRepository.deleteById(id);
    }

}

