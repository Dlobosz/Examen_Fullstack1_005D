package com.example.reviews.controller;

import com.example.reviews.dto.ApiResponse;
import com.example.reviews.dto.ReviewRequestDTO;
import com.example.reviews.model.Review;
import com.example.reviews.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @Operation(summary = "Crear una nueva reseña", description = "Registra una reseña asociada a un destino validando el token de usuario")
    @PostMapping
    public ResponseEntity<ApiResponse<Review>> createReview(
            @Valid @RequestBody ReviewRequestDTO dto,
            @RequestHeader("Authorization") String token) {
        
        Review created = service.createReview(dto, token);
        ApiResponse<Review> response = new ApiResponse<>("Reseña creada con éxito", created, HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar reseñas por destino", description = "Obtiene todas las reseñas de un destino específico")
    @GetMapping("/destination/{destinationId}")
    public ResponseEntity<ApiResponse<List<Review>>> getReviewsByDestination(@PathVariable Long destinationId) {
        
        List<Review> reviews = service.getReviewsByDestination(destinationId);
        ApiResponse<List<Review>> response = new ApiResponse<>("Reseñas obtenidas con éxito", reviews, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}