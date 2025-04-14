package com.example.miniproject2.controllers;

import com.example.miniproject2.models.Captain;
import com.example.miniproject2.services.CaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/captain")
public class CaptainController {

    private final CaptainService captainService;

    @Autowired
    public CaptainController(CaptainService captainService) {
        this.captainService = captainService;
    }

    @PostMapping("/addCaptain")
    public Captain addCaptain(@RequestBody Captain captain) {
        if (captain == null || captain.getLicenseNumber() == null || captain.getName() == null) {
            return null;
        }
        return captainService.addCaptain(captain);
    }

    @GetMapping("/allCaptains")
    public List<Captain> getAllCaptains() {
        List<Captain> captains = captainService.getAllCaptains();
        return captains != null ? captains : Collections.emptyList();
    }

    @GetMapping("/{id}")
    public Captain getCaptainById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        return captainService.getCaptainById(id);
    }

    @GetMapping("/filterByRating")
    public List<Captain> getCaptainsByRating(@RequestParam Double ratingThreshold) {
        if (ratingThreshold == null || ratingThreshold < 0) {
            return Collections.emptyList();
        }
        List<Captain> captains = captainService.getCaptainsByRating(ratingThreshold);
        return captains != null ? captains : Collections.emptyList();
    }

    @GetMapping("/filterByLicenseNumber")
    public Captain getCaptainByLicenseNumber(@RequestParam String licenseNumber) {
        if (licenseNumber == null || licenseNumber.isBlank()) {
            return null;
        }
        return captainService.getCaptainByLicenseNumber(licenseNumber);
    }
}
