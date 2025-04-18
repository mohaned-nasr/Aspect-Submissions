package com.example.lab3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id") // foreign key to Author
    private Author author;

    // Public no‑arg constructor (required by JPA)
    public Book() {
    }

    // ——— Getters ———

    /** The database ID of this book. */
    public Long getId() {
        return id;
    }

    /** The title of this book. */
    public String getTitle() {
        return title;
    }

    /** The author of this book. */
    public Author getAuthor() {
        return author;
    }

    // ——— Setters ———

    /** Set or change the title. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Associate this book with an Author. */
    public void setAuthor(Author author) {
        this.author = author;
    }
}
