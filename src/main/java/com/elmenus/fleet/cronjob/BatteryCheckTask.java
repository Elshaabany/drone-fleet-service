package com.elmenus.fleet.cronjob;

import com.elmenus.fleet.dao.BatteryHistoryDAO;
import com.elmenus.fleet.dao.DroneDAO;
import com.elmenus.fleet.entity.BatteryHistory;
import com.elmenus.fleet.entity.Drone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Component
public class BatteryCheckTask {
    private DroneDAO droneDAO;
    private BatteryHistoryDAO batteryHistoryDAO;

    @Autowired
    public BatteryCheckTask(DroneDAO droneDAO, BatteryHistoryDAO batteryHistoryDAO) {
        this.droneDAO = droneDAO;
        this.batteryHistoryDAO = batteryHistoryDAO;
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void checkBatteryLevels() {
        List<Drone> drones = droneDAO.findAll();
        for (Drone drone : drones) {
            Integer batteryLevel = drone.getBatteryCapacity();
            BatteryHistory batteryHistory = new BatteryHistory();
            batteryHistory.setDrone(drone);
            batteryHistory.setRecordedAt(new Timestamp(new Date().getTime()));
            batteryHistory.setRemainingCapacity(batteryLevel);
            batteryHistoryDAO.save(batteryHistory);
        }
    }
}
