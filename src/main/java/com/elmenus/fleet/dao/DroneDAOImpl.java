package com.elmenus.fleet.dao;

import com.elmenus.fleet.entity.Drone;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DroneDAOImpl implements DroneDAO{

    private EntityManager entityManager;

    @Autowired
    public DroneDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Drone drone) {
        entityManager.persist(drone);
    }

    @Override
    public Drone findDroneById(Long id) {
        return entityManager.find(Drone.class, id);
    }

    @Override
    public Drone findDroneBySerialNumber(String serial) {
        return entityManager
                .createQuery("from Drone where serial_number = :serial", Drone.class)
                .setParameter("serial", serial)
                .getSingleResult();
    }

    @Override
    public List<Drone> getDroneByStatus(Drone.DroneStatus status) {
        return entityManager
                .createQuery("from Drone where status = :status", Drone.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    @Transactional
    public void update(Drone drone) {
        entityManager.merge(drone);
    }
}
