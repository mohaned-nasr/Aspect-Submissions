package com.example.lab3.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateBookDTO(
        @NotBlank String title,
        @NotNull  Long   authorId
) {}

