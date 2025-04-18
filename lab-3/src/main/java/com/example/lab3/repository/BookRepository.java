// repository/BookRepository.java
package com.example.lab3.repository;

import com.example.lab3.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface BookRepository extends JpaRepository<Book, Long> {

    /* Example convenience finder */
    List<Book> findByAuthorId(Long authorId);

    /* Spring automatically implements:
          - findAll()
          - findById()
          - save()
          - deleteById()
       …and many more from JpaRepository                 */
}
