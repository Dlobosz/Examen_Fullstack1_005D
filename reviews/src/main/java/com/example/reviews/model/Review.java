package com.example.reviews.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long destinationId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false, length = 500)
    private String comment;
}