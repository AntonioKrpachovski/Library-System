package library.management.librarymanagement.service;

import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.dtos.AuthorDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author AddAuthor(AuthorDTO authorInfo);
    List<Author> GetAllAuthors();
    Author EditAuthor(Long id, AuthorDTO authorInfo);
    List<Author> GetAllAuthorsByName(String name);
    Optional<Author> GetById(Long id);
    long CountAuthors();
}
