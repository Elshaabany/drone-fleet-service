package com.elmenus.fleet.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 50, nullable = false, unique = true)
    private String code;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "image_url")
    private String imageURL;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(name = "drone_load_has_medication",
            joinColumns = @JoinColumn(name = "medication_id"),
            inverseJoinColumns = @JoinColumn(name = "load_id")
    )
    private List<DroneLoad> droneLoads;

    public Medication() {
    }

    public Medication(String code, String name, Double weight, String imageURL) {
        this.code = code;
        this.name = name;
        this.weight = weight;
        this.imageURL = imageURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<DroneLoad> getLoads() {
        return droneLoads;
    }

    public void setLoads(List<DroneLoad> droneLoads) {
        this.droneLoads = droneLoads;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
