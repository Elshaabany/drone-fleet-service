package com.elmenus.fleet.service;

import com.elmenus.fleet.entity.Drone;
import com.elmenus.fleet.entity.Load;

import java.util.List;

public interface DroneService {
    Drone registerDrone(Drone drone);

    Drone loadDrone(Long id, Load load);

    List<Load> getLoadedMedications(Long id);

    List<Drone> getAvailableDrones();

    Integer checkBatteryLevel(Long id);

}
