package com.elmenus.fleet.dao;

import com.elmenus.fleet.entity.DroneLoad;

public interface LoadDAO {

    DroneLoad save(DroneLoad droneLoad);

    DroneLoad findLoadByIdJoinFetch(Long id);

}
