package com.elmenus.fleet.entity;

import jakarta.persistence.*;

@Entity
@Table(name="drone_model")
public class DroneModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model", nullable = false, length = 50, unique = true)
    private String model;

    @Column(name = "max_weight", nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double maxWeight;

    public DroneModel() {

    }

    public DroneModel(String model, Double maxWeight) {
        this.model = model;
        this.maxWeight = maxWeight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    @Override
    public String toString() {
        return "DroneModel{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", maxWeight=" + maxWeight +
                '}';
    }
}
