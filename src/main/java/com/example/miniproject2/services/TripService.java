package com.example.miniproject2.services;

import com.example.miniproject2.models.Trip;
import com.example.miniproject2.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }


    public Trip addTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }


    public Trip getTripById(Long id) {
        Optional<Trip> trip = tripRepository.findById(id);
        return trip.orElse(null); // Or throw exception
    }


    public Trip updateTrip(Long id, Trip updatedTrip) {
        return tripRepository.findById(id).map(existing -> {
            existing.setOrigin(updatedTrip.getOrigin());
            existing.setDestination(updatedTrip.getDestination());
            existing.setTripCost(updatedTrip.getTripCost());
            existing.setTripDate(updatedTrip.getTripDate());
            return tripRepository.save(existing);
        }).orElse(null); // Or throw exception
    }


    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }


    public List<Trip> findTripsWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return tripRepository.findByTripDateBetween(startDate, endDate);
    }


    public List<Trip> findTripsByCaptainId(Long captainId) {
        return tripRepository.findByCaptainId(captainId);
    }
}