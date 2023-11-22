package com.elmenus.fleet.service;

import com.elmenus.fleet.dao.DroneDAO;
import com.elmenus.fleet.dao.DroneModelDAO;
import com.elmenus.fleet.dao.LoadDAO;
import com.elmenus.fleet.dao.MedicationDAO;
import com.elmenus.fleet.dto.DroneDTO;
import com.elmenus.fleet.dto.LoadDTO;
import com.elmenus.fleet.entity.Drone;
import com.elmenus.fleet.entity.DroneModel;
import com.elmenus.fleet.entity.Load;
import com.elmenus.fleet.entity.Medication;
import com.elmenus.fleet.exception.DroneLoadingException;
import com.elmenus.fleet.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneServiceImpl implements DroneService{

    private DroneDAO droneDAO;
    private LoadDAO loadDAO;
    private MedicationDAO medicationDAO;

    private DroneModelDAO droneModelDAO;

    @Autowired
    public DroneServiceImpl(DroneDAO droneDAO, LoadDAO loadDAO, MedicationDAO medicationDAO, DroneModelDAO droneModelDAO) {
        this.droneDAO = droneDAO;
        this.loadDAO = loadDAO;
        this.medicationDAO = medicationDAO;
        this.droneModelDAO = droneModelDAO;
    }

    @Override
    public Drone registerDrone(DroneDTO droneDTO) {

        DroneModel droneModel = droneModelDAO.findDroneModelByName(droneDTO.getDroneModel());
        Drone drone = new Drone();
        drone.setSerialNumber(droneDTO.getSerialNumber());
        drone.setBatteryCapacity(droneDTO.getBatteryCapacity());
        drone.setStatus(droneDTO.getStatus());
        drone.setDroneModel(droneModel);
        return droneDAO.save(drone);
    }

    @Override
    public Drone loadDrone(Long id, LoadDTO loadDTO) {
        Drone drone = droneDAO.findDroneById(id);
        if (drone == null) {
            throw new NotFoundException(Drone.class.getSimpleName(), id);
        }

        if (!drone.getStatus().equals(Drone.DroneStatus.IDLE)) {
            throw new IllegalStateException("Drone is not in IDLE state");
        }

        Load load = new Load();
        Double totalWeight = 0d;

        for(String code : loadDTO.getMedicationCodes()) {
            Medication med = medicationDAO.findMedicationByCode(code);
            if(med == null) {
                throw new NotFoundException(Medication.class.getSimpleName(), code);
            }
            totalWeight += med.getWeight();
            load.getMedications().add(med);
        }

        load.setWeight(totalWeight);

        if(drone.getDroneModel().getMaxWeight() < load.getWeight()) {
            load.setStatus(Load.LoadStatus.REJECTED);
            load.setMessage("loading failed: weight is more than the drone can carry");
            loadDAO.save(load);
            throw new DroneLoadingException(load.getMessage());
        }

        if (drone.getBatteryCapacity() < 25) {
            load.setStatus(Load.LoadStatus.REJECTED);
            load.setMessage("loading failed: drone battery is low");
            loadDAO.save(load);
            throw new DroneLoadingException(load.getMessage());
        }

        Load savedLoad = loadDAO.save(load);
        drone.setLoad(savedLoad);
        drone.setStatus(Drone.DroneStatus.LOADING);
        return droneDAO.save(drone);
    }

    @Override
    public List<Load> getLoadedMedications(Long id) {
        return null;
    }

    @Override
    public List<Drone> getAvailableDrones() {
        return null;
    }

    @Override
    public Integer checkBatteryLevel(Long id) {
        return null;
    }
}
