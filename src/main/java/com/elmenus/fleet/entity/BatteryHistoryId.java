package com.elmenus.fleet.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class BatteryHistoryId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;

    @Column(name = "recorded_at", nullable = false)
    private Timestamp recordedAt;

    public BatteryHistoryId() {
    }

    public BatteryHistoryId(Drone drone, Timestamp recordedAt) {
        this.drone = drone;
        this.recordedAt = recordedAt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatteryHistoryId that = (BatteryHistoryId) o;

        if (!drone.equals(that.drone)) return false;
        return recordedAt.equals(that.recordedAt);
    }

    @Override
    public int hashCode() {
        int result = drone.hashCode();
        result = 31 * result + recordedAt.hashCode();
        return result;
    }
}
