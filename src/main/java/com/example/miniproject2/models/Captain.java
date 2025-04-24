package com.example.miniproject2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "captains")
@Getter
@Setter
public class Captain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String licenseNumber;
    private Double avgRatingScore;

    @OneToMany(mappedBy = "captain")
    private List<Trip> trips;

    // Default constructor
    public Captain() {}

    // Partial constructor
    public Captain(String name, String licenseNumber) {
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    // Full constructor
    public Captain(Long id, String name, String licenseNumber, Double avgRatingScore) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.avgRatingScore = avgRatingScore;
    }

    public Captain(String name, String licenseNumber, double avgRatingScore) {
     this.name = name;
     this.licenseNumber = licenseNumber;
     this.avgRatingScore = avgRatingScore;
    }

    // Getters and setters...
}
