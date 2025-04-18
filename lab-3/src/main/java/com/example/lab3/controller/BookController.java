package com.example.lab3.controller;

import com.example.lab3.dto.AuthorDTO;
import com.example.lab3.dto.BookDTO;
import com.example.lab3.dto.CreateBookDTO;
import com.example.lab3.dto.UpdateBookDTO;
import com.example.lab3.model.Book;
import com.example.lab3.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@Valid @RequestBody CreateBookDTO dto) {
        Book b = bookService.create(dto);
        return toDto(b);
    }

    @GetMapping
    public List<BookDTO> list() {
        return bookService.list()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public BookDTO getById(@PathVariable Long id) {
        Book b = bookService.get(id);
        return toDto(b);
    }

    @PutMapping("/{id}")
    public BookDTO update(@PathVariable Long id,
                          @Valid @RequestBody UpdateBookDTO dto) {
        Book b = bookService.update(id, dto);
        return toDto(b);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    private BookDTO toDto(Book b) {
        return new BookDTO(
                b.getId(),
                b.getTitle(),
                new AuthorDTO(
                        b.getAuthor().getId(),
                        b.getAuthor().getName()
                )
        );
    }
}
