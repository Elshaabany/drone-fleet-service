package com.elmenus.fleet.service;

import com.elmenus.fleet.dao.DroneDAO;
import com.elmenus.fleet.dao.LoadDAO;
import com.elmenus.fleet.dao.MedicationDAO;
import com.elmenus.fleet.entity.Drone;
import com.elmenus.fleet.entity.Load;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DroneServiceImpl implements DroneService{

    private DroneDAO droneDAO;
    private LoadDAO loadDAO;
    private MedicationDAO medicationDAO;

    @Autowired
    public DroneServiceImpl(DroneDAO droneDAO, LoadDAO loadDAO, MedicationDAO medicationDAO) {
        this.droneDAO = droneDAO;
        this.loadDAO = loadDAO;
        this.medicationDAO = medicationDAO;
    }

    @Override
    public Drone registerDrone(Drone drone) {
        return null;
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
