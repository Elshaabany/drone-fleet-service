package com.elmenus.fleet.dao;

import com.elmenus.fleet.entity.Medication;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MedicationDAOImpl implements MedicationDAO{

    private EntityManager entityManager;

    @Autowired
    public MedicationDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Medication findMedicationByCode(String code) {
        return entityManager
                .createQuery("from Medication where code = :code", Medication.class)
                .setParameter("code", code)
                .getSingleResult();
    }
}
