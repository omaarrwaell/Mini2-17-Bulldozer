package com.example.miniproject2.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "ratings")
@Getter
@Setter
public class Rating {

    @Id
    private String id;

    private Long entityId;
    private String entityType;
    private Integer score;
    private String comment;
    private LocalDateTime ratingDate;

    public Rating() {}

    public Rating(Long entityId, String entityType, Integer score) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.score = score;
    }

    public Rating(String id, Long entityId, String entityType, Integer score, String comment, LocalDateTime ratingDate) {
        this.id = id;
        this.entityId = entityId;
        this.entityType = entityType;
        this.score = score;
        this.comment = comment;
        this.ratingDate = ratingDate;
    }

    // Getters and setters...
}