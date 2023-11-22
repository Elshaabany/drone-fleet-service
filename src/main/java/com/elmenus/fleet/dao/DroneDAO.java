package com.elmenus.fleet.dao;

import com.elmenus.fleet.entity.Drone;

import java.util.List;

public interface DroneDAO {

    void save(Drone drone);

    Drone findDroneById(Long id);

    Drone findDroneBySerialNumber(String serial);

    List<Drone> getDroneByStatus(Drone.DroneStatus status);

    void update(Drone drone);
}
