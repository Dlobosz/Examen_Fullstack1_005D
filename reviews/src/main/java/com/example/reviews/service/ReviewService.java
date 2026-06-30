package com.example.reviews.service;

import com.example.reviews.dto.ReviewRequestDTO;
import com.example.reviews.model.Review;
import com.example.reviews.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository repository;
    private final WebClient.Builder webClientBuilder;

    public Review createReview(ReviewRequestDTO dto, String authHeader) {
        log.info("Iniciando validación para crear reseña del destino ID: {}", dto.getDestinationId());

        // 1. Aquí validamos el Token con el Login Service usando WebClient
        log.info("Validando token con Login Service...");
        try {
            // Se hace la llamada al microservicio "login" registrado en Eureka
            webClientBuilder.build().get()
                    .uri("http://login/api/v1/auth/validate") // Ajustar ruta según el Login Service real
                    .header("Authorization", authHeader)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (Exception e) {
            log.error("Error al validar el token: {}", e.getMessage());
            throw new RuntimeException("Token inválido o servicio de Login no disponible");
        }

        // Simulamos la extracción del username del token (puedes ajustarlo luego)
        String username = "usuario_validado"; 

        // 2. Guardamos la reseña
        Review review = Review.builder()
                .destinationId(dto.getDestinationId())
                .username(username)
                .rating(dto.getRating())
                .comment(dto.getComment())
                .build();

        log.info("Reseña guardada con éxito en la base de datos.");
        return repository.save(review);
    }

    public List<Review> getReviewsByDestination(Long destinationId) {
        return repository.findByDestinationId(destinationId);
    }
}