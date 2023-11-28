package com.elmenus.fleet.service;

import com.elmenus.fleet.exception.DroneNotLoadedException;
import com.elmenus.fleet.exception.DuplicateSerialNumberException;
import com.elmenus.fleet.repository.DroneRepository;
import com.elmenus.fleet.repository.DroneModelRepository;
import com.elmenus.fleet.repository.DroneLoadRepository;
import com.elmenus.fleet.repository.MedicationRepository;
import com.elmenus.fleet.dto.DroneDTO;
import com.elmenus.fleet.dto.LoadDTO;
import com.elmenus.fleet.entity.Drone;
import com.elmenus.fleet.entity.DroneLoad;
import com.elmenus.fleet.entity.DroneModel;
import com.elmenus.fleet.entity.Medication;
import com.elmenus.fleet.exception.DroneLoadingException;
import com.elmenus.fleet.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneServiceImpl implements DroneService{

    private DroneRepository droneRepository;
    private DroneLoadRepository droneLoadRepository;
    private MedicationRepository medicationRepository;

    private DroneModelRepository droneModelRepository;

    @Autowired
    public DroneServiceImpl(DroneRepository droneRepository, DroneLoadRepository droneLoadRepository, MedicationRepository medicationRepository, DroneModelRepository droneModelRepository) {
        this.droneRepository = droneRepository;
        this.droneLoadRepository = droneLoadRepository;
        this.medicationRepository = medicationRepository;
        this.droneModelRepository = droneModelRepository;
    }

    @Override
    public Drone registerDrone(DroneDTO droneDTO) {
        if(droneRepository.existsBySerialNumber(droneDTO.getSerialNumber())){
            throw new DuplicateSerialNumberException("Drone with the provided serial number already exists");
        }
        DroneModel droneModel = droneModelRepository.findByModel(droneDTO.getDroneModel());
        if (droneModel == null) {
            throw new NotFoundException(DroneModel.class.getSimpleName(), droneDTO.getDroneModel());
        }
        Drone drone = new Drone();
        drone.setSerialNumber(droneDTO.getSerialNumber());
        drone.setBatteryCapacity(droneDTO.getBatteryCapacity());
        drone.setStatus(droneDTO.getStatus() == null ? Drone.DroneStatus.IDLE : droneDTO.getStatus());
        drone.setDroneModel(droneModel);
        return droneRepository.save(drone);
    }

    @Override
    public Drone loadDrone(Long id, LoadDTO loadDTO) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Drone.class.getSimpleName(), id));

        if (!drone.getStatus().equals(Drone.DroneStatus.IDLE)) {
            throw new DroneLoadingException("Drone is not in IDLE state");
        }

        DroneLoad droneLoad = new DroneLoad();
        Double totalWeight = 0d;

        for(String code : loadDTO.getMedicationCodes()) {
            Medication med = medicationRepository.findByCode(code);
            if(med == null) {
                throw new NotFoundException(Medication.class.getSimpleName(), code);
            }
            totalWeight += med.getWeight();
            droneLoad.addMedications(med);
        }

        droneLoad.setWeight(totalWeight);

        if(drone.getDroneModel().getMaxWeight() < droneLoad.getWeight()) {
            droneLoad.setStatus(DroneLoad.LoadStatus.REJECTED);
            droneLoad.setMessage("loading failed: weight is more than the drone can carry");
            droneLoadRepository.save(droneLoad);
            throw new DroneLoadingException(droneLoad.getMessage());
        }

        if (drone.getBatteryCapacity() < 25) {
            droneLoad.setStatus(DroneLoad.LoadStatus.REJECTED);
            droneLoad.setMessage("loading failed: drone battery is low");
            droneLoadRepository.save(droneLoad);
            throw new DroneLoadingException(droneLoad.getMessage());
        }
        droneLoad.setStatus(DroneLoad.LoadStatus.ASSIGNED);
        DroneLoad savedDroneLoad = droneLoadRepository.save(droneLoad);
        drone.setLoad(savedDroneLoad);
        drone.setStatus(Drone.DroneStatus.LOADED);
        return droneRepository.save(drone);
    }

    @Override
    public List<Medication> getLoadedMedications(Long id) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Drone.class.getSimpleName(), id));
        DroneLoad load = drone.getLoad();
        if (load == null) {
            throw new DroneNotLoadedException("The drone doesn't have any load");
        }
        return load.getMedications();
    }

    @Override
    public List<Drone> getAvailableDrones(Drone.DroneStatus status) {
        return status == null ?
                droneRepository.findAll()  :
                droneRepository.findByStatus(status);
    }

    @Override
    public Integer checkBatteryLevel(Long id) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Drone.class.getSimpleName(), id));
        return drone.getBatteryCapacity();
    }
}
