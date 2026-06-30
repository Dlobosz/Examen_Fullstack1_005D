package com.example.reviews.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRequestDTO {

    @NotNull(message = "El ID del destino es obligatorio")
    private Long destinationId;

    @NotNull(message = "La nota es obligatoria")
    @Min(value = 1, message = "La nota mínima es 1")
    @Max(value = 5, message = "La nota máxima es 5")
    private Integer rating;

    @NotBlank(message = "El comentario no puede estar vacío")
    private String comment;
}