// src/main/java/com/example/lab3/dto/BookDTO.java
package com.example.lab3.dto;

public record BookDTO(
        Long       id,
        String     title,
        AuthorDTO  author
) {}
