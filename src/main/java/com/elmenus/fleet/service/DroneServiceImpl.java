package com.elmenus.fleet.service;

import com.elmenus.fleet.dao.DroneDAO;
import com.elmenus.fleet.dao.DroneModelDAO;
import com.elmenus.fleet.dao.LoadDAO;
import com.elmenus.fleet.dao.MedicationDAO;
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
            throw new RuntimeException("Drone is not in IDLE state");
        }

        DroneLoad droneLoad = new DroneLoad();
        Double totalWeight = 0d;

        for(String code : loadDTO.getMedicationCodes()) {
            Medication med = medicationDAO.findMedicationByCode(code);
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
            loadDAO.save(droneLoad);
            throw new DroneLoadingException(droneLoad.getMessage());
        }

        if (drone.getBatteryCapacity() < 25) {
            droneLoad.setStatus(DroneLoad.LoadStatus.REJECTED);
            droneLoad.setMessage("loading failed: drone battery is low");
            loadDAO.save(droneLoad);
            throw new DroneLoadingException(droneLoad.getMessage());
        }
        droneLoad.setStatus(DroneLoad.LoadStatus.ASSIGNED);
        DroneLoad savedDroneLoad = loadDAO.save(droneLoad);
        drone.setLoad(savedDroneLoad);
        drone.setStatus(Drone.DroneStatus.LOADED);
        return droneDAO.save(drone);
    }

    @Override
    public List<Medication> getLoadedMedications(Long id) {
        Drone drone = droneDAO.findDroneById(id);
        if (drone == null) {
            throw new NotFoundException(Drone.class.getSimpleName(), id);
        }
        DroneLoad load = drone.getLoad();
        if (load == null) {
            throw new RuntimeException("The drone doesn't have any load");
        }
        return load.getMedications();
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
