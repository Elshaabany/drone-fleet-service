package com.elmenus.fleet.dto;

import com.elmenus.fleet.entity.Drone;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DroneDTO {

    @NotNull
    @Size(max = 100)
    private String serialNumber;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer batteryCapacity;

    private Drone.DroneStatus status;

    @NotNull
    @Size(max = 50)
    private String droneModel;

    public DroneDTO() {
    }

    public DroneDTO(String serialNumber, Integer batteryCapacity, Drone.DroneStatus status, String droneModel) {
        this.serialNumber = serialNumber;
        this.batteryCapacity = batteryCapacity;
        this.status = status;
        this.droneModel = droneModel;
    }

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
