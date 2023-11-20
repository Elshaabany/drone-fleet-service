package com.elmenus.fleet.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Drone")
public class Drone {

    public enum DroneStatus {
        IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number", length = 100, nullable = false)
    private String serialNumber;

    @Column(name = "battery_capacity", nullable = false, columnDefinition = "INT CHECK (battery_capacity >= 0 AND battery_capacity <= 100)")
    private Integer batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DroneStatus status;

    @ManyToOne
    @JoinColumn(name = "Drone_Model_id", nullable = false)
    private DroneModel droneModel;

    @OneToOne
    @JoinColumn(name = "Current_Load_id")
    private Load currentLoad;

    public Drone() {}
    public Drone(String serialNumber, Integer batteryCapacity, DroneStatus status, DroneModel droneModel, Load currentLoad) {
        this.serialNumber = serialNumber;
        this.batteryCapacity = batteryCapacity;
        this.status = status;
        this.droneModel = droneModel;
        this.currentLoad = currentLoad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public DroneStatus getStatus() {
        return status;
    }

    public void setStatus(DroneStatus status) {
        this.status = status;
    }

    public DroneModel getDroneModel() {
        return droneModel;
    }

    public void setDroneModel(DroneModel droneModel) {
        this.droneModel = droneModel;
    }

    public Load getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(Load currentLoad) {
        this.currentLoad = currentLoad;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", batteryCapacity=" + batteryCapacity +
                ", status=" + status +
                ", droneModel=" + droneModel +
                ", currentLoad=" + currentLoad +
                '}';
    }

}
