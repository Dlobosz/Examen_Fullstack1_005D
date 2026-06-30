package com.example.reviews.service;

import com.example.reviews.model.Review;
import com.example.reviews.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository repository;

    @Mock
    private WebClient.Builder webClientBuilder;

    @InjectMocks
    private ReviewService service;

    @Test
    void getReviewsByDestination_deberiaRetornarListaDeResenas() {
        // 1. Preparar los datos falsos (Mock)
        Long destinationId = 1L;
        Review review1 = Review.builder().id(1L).destinationId(destinationId).username("user1").rating(5).comment("Genial").build();
        Review review2 = Review.builder().id(2L).destinationId(destinationId).username("user2").rating(4).comment("Bueno").build();
        
        when(repository.findByDestinationId(destinationId)).thenReturn(Arrays.asList(review1, review2));

        // 2. Ejecutar el método
        List<Review> resultado = service.getReviewsByDestination(destinationId);

        // 3. Verificar (Asserts)
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Genial", resultado.get(0).getComment());
    }
}