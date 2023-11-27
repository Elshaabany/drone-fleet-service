package com.elmenus.fleet.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "battery_history")
public class BatteryHistory implements Serializable {

    @EmbeddedId
    private BatteryHistoryId batteryHistoryId;

    @Column(name = "remaining_capacity", nullable = false, columnDefinition = "INT CHECK (remaining_capacity >= 0 AND remaining_capacity <= 100)")
    private Integer remainingCapacity;

    public BatteryHistory() {
    }

    public BatteryHistory(BatteryHistoryId batteryHistoryId, Integer remainingCapacity) {
        this.batteryHistoryId = batteryHistoryId;
        this.remainingCapacity = remainingCapacity;
    }

    public BatteryHistoryId getBatteryHistoryId() {
        return batteryHistoryId;
    }

    public void setBatteryHistoryId(BatteryHistoryId batteryHistoryId) {
        this.batteryHistoryId = batteryHistoryId;
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

        if (!batteryHistoryId.equals(that.batteryHistoryId)) return false;
        return remainingCapacity.equals(that.remainingCapacity);
    }

    @Override
    public int hashCode() {
        int result = batteryHistoryId.hashCode();
        result = 31 * result + remainingCapacity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BatteryHistory{" +
                "batteryHistoryId=" + batteryHistoryId +
                ", remainingCapacity=" + remainingCapacity +
                '}';
    }
}
