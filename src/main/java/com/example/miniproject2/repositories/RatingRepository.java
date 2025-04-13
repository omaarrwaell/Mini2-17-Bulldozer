package com.example.miniproject2.repositories;

import com.example.miniproject2.models.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    // Ratings by entityId and entityType (e.g., trip, customer, captain)
    List<Rating> findByEntityIdAndEntityType(Long entityId, String entityType);

    // Ratings with score greater than a value
    List<Rating> findByScoreGreaterThan(Integer score);
}