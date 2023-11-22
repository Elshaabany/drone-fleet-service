package com.elmenus.fleet.service;

import com.elmenus.fleet.dto.DroneDTO;
import com.elmenus.fleet.dto.LoadDTO;
import com.elmenus.fleet.entity.Drone;
import com.elmenus.fleet.entity.Load;

import java.util.List;

public interface DroneService {
    Drone registerDrone(DroneDTO droneDTO);

    Drone loadDrone(Long id, LoadDTO loadDTO);

    List<Load> getLoadedMedications(Long id);

    List<Drone> getAvailableDrones();

    Integer checkBatteryLevel(Long id);

}
