package com.elmenus.fleet.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "battery_history")
public class BatteryHistory implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;

    @Id
    @Column(name = "recorded_at", nullable = false)
    private Timestamp recordedAt;

    @Column(name = "remaining_capacity", nullable = false, columnDefinition = "INT CHECK (remaining_capacity >= 0 AND remaining_capacity <= 100)")
    private Integer remainingCapacity;

    public BatteryHistory() {
    }

    public BatteryHistory(Drone drone, Timestamp recordedAt, Integer remainingCapacity) {
        this.drone = drone;
        this.recordedAt = recordedAt;
        this.remainingCapacity = remainingCapacity;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public Timestamp getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(Timestamp recordedAt) {
        this.recordedAt = recordedAt;
    }

    public Integer getRemainingCapacity() {
        return remainingCapacity;
    }

    public void setRemainingCapacity(Integer remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatteryHistory that = (BatteryHistory) o;

        if (!drone.equals(that.drone)) return false;
        return recordedAt.equals(that.recordedAt);
    }

    @Override
    public int hashCode() {
        int result = drone.hashCode();
        result = 31 * result + recordedAt.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BatteryHistory{" +
                "drone=" + drone +
                ", recordedAt=" + recordedAt +
                ", remainingCapacity=" + remainingCapacity +
                '}';
    }
}
