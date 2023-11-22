package com.elmenus.fleet.dao;

import com.elmenus.fleet.entity.Load;
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
    public void save(Load load) {
        entityManager.persist(load);
    }

    @Override
    @Transactional
    public void update(Load load) {
        entityManager.merge(load);
    }

    @Override
    public Load findLoadByIdJoinFetch(Long id) {
        return entityManager
                .createQuery(
                        "select l from Load l "
                        + "JOIN FETCH l.medications "
                        + "where l.id = :id", Load.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
