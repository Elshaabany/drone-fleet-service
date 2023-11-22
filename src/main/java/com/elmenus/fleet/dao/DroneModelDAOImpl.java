package com.elmenus.fleet.dao;

import com.elmenus.fleet.entity.Drone;
import com.elmenus.fleet.entity.DroneModel;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DroneModelDAOImpl implements DroneModelDAO{

    private EntityManager entityManager;

    @Autowired
    public DroneModelDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public DroneModel findDroneModelByName(String droneModel) {
        return entityManager
                .createQuery("from DroneModel where model = :droneModel", DroneModel.class)
                .setParameter("droneModel", droneModel)
                .getSingleResult();
    }
}
