package com.elmenus.fleet.dao;

import com.elmenus.fleet.entity.BatteryHistory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BatteryHistoryDAOImpl implements BatteryHistoryDAO{

    private EntityManager entityManager;

    @Autowired
    public BatteryHistoryDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public BatteryHistory save(BatteryHistory batteryHistory) {
        return entityManager.merge(batteryHistory);
    }
}
