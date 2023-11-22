package com.elmenus.fleet.service;

import com.elmenus.fleet.dao.DroneDAO;
import com.elmenus.fleet.dao.DroneModelDAO;
import com.elmenus.fleet.dao.LoadDAO;
import com.elmenus.fleet.dao.MedicationDAO;
import com.elmenus.fleet.dto.DroneDTO;
import com.elmenus.fleet.entity.Drone;
import com.elmenus.fleet.entity.DroneModel;
import com.elmenus.fleet.entity.Load;
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
    public Drone loadDrone(Long id, Load load) {
        return null;
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
