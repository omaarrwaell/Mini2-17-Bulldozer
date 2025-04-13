package com.example.miniproject2.repositories;



import com.example.miniproject2.models.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaptainRepository extends JpaRepository<Captain, Long> {

    // Find all captains with rating above a threshold
    List<Captain> findByAvgRatingScoreGreaterThan(Double threshold);

    // Find captain by license number
    Captain findByLicenseNumber(String licenseNumber);
}
