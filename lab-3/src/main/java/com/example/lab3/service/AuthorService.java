package com.example.lab3.service;

import com.example.lab3.dto.CreateAuthorDTO;
import com.example.lab3.dto.UpdateAuthorDTO;
import com.example.lab3.model.Author;
import com.example.lab3.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository repo;

    public AuthorService(AuthorRepository repo) {   // explicit constructor
        this.repo = repo;
    }

    public Author create(CreateAuthorDTO dto) {
        Author author = new Author();
        author.setName(dto.name());
        return repo.save(author);
    }

    public Author update(Long id, UpdateAuthorDTO dto) {
        Author author = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        author.setName(dto.name());
        return repo.save(author);
    }

    public Author get(Long id) { return repo.findById(id).orElseThrow(); }

    public List<Author> list() { return repo.findAll(); }

    public void delete(Long id) { repo.deleteById(id); }
}
