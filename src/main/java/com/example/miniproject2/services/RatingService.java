package com.example.miniproject2.services;

import com.example.miniproject2.models.Rating;
import com.example.miniproject2.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }


    public Rating updateRating(String id, Rating updatedRating) {
        // Check if rating exists
        if (ratingRepository.existsById(id)) {
            updatedRating.setId(id); // Ensure ID is set correctly
            return ratingRepository.save(updatedRating);
        }
        return null; // Or throw exception - Rating not found
    }


    public void deleteRating(String id) {
        ratingRepository.deleteById(id);
    }


    public List<Rating> getRatingsByEntity(Long entityId, String entityType) {
        return ratingRepository.findByEntityIdAndEntityType(entityId, entityType);
    }


    public List<Rating> findRatingsAboveScore(int minScore) {
        return ratingRepository.findByScoreGreaterThan(minScore);
    }
}