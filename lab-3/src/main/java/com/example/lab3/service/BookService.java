
package com.example.lab3.service;

import com.example.lab3.dto.CreateBookDTO;
import com.example.lab3.dto.UpdateBookDTO;
import com.example.lab3.model.Book;
import com.example.lab3.model.Author;
import com.example.lab3.repository.BookRepository;
import com.example.lab3.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {
    private final BookRepository   bookRepo;
    private final AuthorRepository authorRepo;

    public BookService(BookRepository bookRepo, AuthorRepository authorRepo) {
        this.bookRepo   = bookRepo;
        this.authorRepo = authorRepo;
    }


    public Book create(CreateBookDTO dto) {
        Author author = authorRepo.findById(dto.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Book b = new Book();
        b.setTitle(dto.title());
        b.setAuthor(author);
        return bookRepo.save(b);
    }

    public Book update(Long id, UpdateBookDTO dto) {
        Book b = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Author author = authorRepo.findById(dto.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        b.setTitle(dto.title());
        b.setAuthor(author);
        return bookRepo.save(b);
    }

    public Book get(Long id)    { return bookRepo.findById(id).orElseThrow(); }
    public List<Book> list()    { return bookRepo.findAll(); }
    public void delete(Long id) { bookRepo.deleteById(id); }
}
