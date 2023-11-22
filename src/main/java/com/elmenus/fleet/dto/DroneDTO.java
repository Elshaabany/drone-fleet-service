package com.elmenus.fleet.dto;

import com.elmenus.fleet.entity.Drone;

public class DroneDTO {

    private String serialNumber;

    private Integer batteryCapacity;

    private Drone.DroneStatus status;

    private String droneModel;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public Drone.DroneStatus getStatus() {
        return status;
    }

    public void setStatus(Drone.DroneStatus status) {
        this.status = status;
    }

    public String getDroneModel() {
        return droneModel;
    }

    public void setDroneModel(String droneModel) {
        this.droneModel = droneModel;
    }
}
