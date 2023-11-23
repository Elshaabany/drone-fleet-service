package com.elmenus.fleet.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drone_load")
public class DroneLoad {

    public enum LoadStatus {
        PENDING, ASSIGNED, REJECTED, DELIVERED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weight", nullable = false, columnDefinition = "DECIMAL(5,2) CHECK (weight > 0)")
    private Double weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoadStatus status;

    @Column(name = "message")
    private String message;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(name = "drone_load_has_medication",
            joinColumns = @JoinColumn(name = "load_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private List<Medication> medications;

    public DroneLoad() {

    }

    public DroneLoad(Double weight, LoadStatus status, String message) {
        this.weight = weight;
        this.status = status;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LoadStatus getStatus() {
        return status;
    }

    public void setStatus(LoadStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public void addMedications(Medication med) {
        if(medications == null) {
            medications = new ArrayList<>();
        }
        medications.add(med);
    }

    @Override
    public String toString() {
        return "Load{" +
                "id=" + id +
                ", weight=" + weight +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
