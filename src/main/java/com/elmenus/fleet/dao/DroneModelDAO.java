package com.elmenus.fleet.dao;

import com.elmenus.fleet.entity.DroneModel;

public interface DroneModelDAO {
    DroneModel findDroneModelByName(String droneModel);
}
