package com.elmenus.fleet.dao;

import com.elmenus.fleet.entity.DroneLoad;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoadDAOImpl implements LoadDAO{

    private EntityManager entityManager;

    @Autowired
    public LoadDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public DroneLoad save(DroneLoad droneLoad) {
        return entityManager.merge(droneLoad);
    }

    @Override
    public DroneLoad findLoadByIdJoinFetch(Long id) {
        return entityManager
                .createQuery(
                        "select l from Load l "
                        + "JOIN FETCH l.medications "
                        + "where l.id = :id", DroneLoad.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
