package com.example.miniproject2.services;

import com.example.miniproject2.models.Rating;
import com.example.miniproject2.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MongoClient mongoClient;
    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }
    @PostConstruct
    public void logMongoConnectionDetails() {
        System.out.println(">>> MongoDB cluster description: " + mongoClient.getClusterDescription());
        System.out.println(">>> Connected to DBs: " + mongoClient.listDatabaseNames().into(new java.util.ArrayList<>()));
        System.out.println(">>> Current MongoDB database being used: " + mongoTemplate.getDb().getName());

    }
    public Rating addRating(Rating rating) {
        System.out.println("Current count in DB: " + ratingRepository.count());
     //   System.out.println(">>> Mongo host: " + mongoTemplate.getMongoDbFactory().getMongoClient().getClusterDescription());

        Rating saved = ratingRepository.save(rating);
        rating.setId(saved.getId());
        System.out.println("Saving rating: " + rating);
        System.out.println("Saved rating with ID: " + saved.getId());
        System.out.println("Current count in DB: " + ratingRepository.count());// ✅ update original object with saved ID
        return saved;

    }


    public Rating updateRating(String id, Rating updatedRating) {
        System.out.println("Current count in DB: " + ratingRepository.count());
        System.out.println(">>> Service updating rating with ID: " + id);
        Optional<Rating> found = ratingRepository.findById(id);
        System.out.println(">>> Found? " + found.isPresent());
        return ratingRepository.findById(id).map(existing -> {
            if (updatedRating.getScore() != null)
                existing.setScore(updatedRating.getScore());
            if (updatedRating.getComment() != null)
                existing.setComment(updatedRating.getComment());
            if (updatedRating.getEntityId() != null)
                existing.setEntityId(updatedRating.getEntityId());
            if (updatedRating.getEntityType() != null)
                existing.setEntityType(updatedRating.getEntityType());
            if (updatedRating.getRatingDate() != null)
                existing.setRatingDate(updatedRating.getRatingDate());

            return ratingRepository.save(existing);
        }).orElse(null);
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