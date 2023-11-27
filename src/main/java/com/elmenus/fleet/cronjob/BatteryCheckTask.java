package com.elmenus.fleet.cronjob;

import com.elmenus.fleet.entity.BatteryHistoryId;
import com.elmenus.fleet.repository.BatteryHistoryRepository;
import com.elmenus.fleet.repository.DroneRepository;
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
    private DroneRepository droneRepository;
    private BatteryHistoryRepository batteryHistoryRepository;

    @Autowired
    public BatteryCheckTask(DroneRepository droneRepository, BatteryHistoryRepository batteryHistoryRepository) {
        this.droneRepository = droneRepository;
        this.batteryHistoryRepository = batteryHistoryRepository;
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void checkBatteryLevels() {
        List<Drone> drones = droneRepository.findAll();
        for (Drone drone : drones) {
            Integer batteryLevel = drone.getBatteryCapacity();
            BatteryHistory batteryHistory = new BatteryHistory();
            batteryHistory.setBatteryHistoryId( new BatteryHistoryId(drone, new Timestamp(new Date().getTime())) );
            batteryHistory.setRemainingCapacity(batteryLevel);
            batteryHistoryRepository.save(batteryHistory);
        }
    }
}
