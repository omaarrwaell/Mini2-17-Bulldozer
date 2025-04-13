package com.example.miniproject2.repositories;

import com.example.miniproject2.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    // Trips between two dates
    List<Trip> findByTripDateBetween(LocalDateTime start, LocalDateTime end);

    // Trips by a specific captain
    List<Trip> findByCaptainId(Long captainId);
}