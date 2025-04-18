package com.example.lab3.controller;

import com.example.lab3.dto.CreateAuthorDTO;
import com.example.lab3.dto.UpdateAuthorDTO;
import com.example.lab3.model.Author;
import com.example.lab3.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {   // constructor injection
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Author> create(@Valid @RequestBody CreateAuthorDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public Author read(@PathVariable Long id){ return service.get(id); }

    @PutMapping("/{id}")
    public Author update(@PathVariable Long id,
                         @Valid @RequestBody UpdateAuthorDTO dto){
        return service.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ service.delete(id); }

    @GetMapping
    public List<Author> list(){ return service.list(); }
}

