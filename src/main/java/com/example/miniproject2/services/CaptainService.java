package com.example.miniproject2.services;

import com.example.miniproject2.models.Captain;
import com.example.miniproject2.repositories.CaptainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaptainService {

    private final CaptainRepository captainRepository;

    @Autowired
    public CaptainService(CaptainRepository captainRepository) {
        this.captainRepository = captainRepository;
    }


    public Captain addCaptain(Captain captain) {
        return captainRepository.save(captain);
    }


    public List<Captain> getAllCaptains() {
        return captainRepository.findAll();
    }


    public Captain getCaptainById(Long id) {
        Optional<Captain> captain = captainRepository.findById(id);
        return captain.orElse(null); // Or throw an exception if preferred
    }


    public List<Captain> getCaptainsByRating(Double ratingThreshold) {
        return captainRepository.findByAvgRatingScoreGreaterThan(ratingThreshold);
    }


    public Captain getCaptainByLicenseNumber(String licenseNumber) {
        return captainRepository.findByLicenseNumber(licenseNumber);
    }
}