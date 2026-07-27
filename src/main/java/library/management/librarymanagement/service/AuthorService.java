package library.management.librarymanagement.service;

import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.dtos.AuthorDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author addAuthor(AuthorDTO authorInfo);
    List<Author> getAllAuthors();
    Author editAuthor(Long id, AuthorDTO authorInfo);
    List<Author> getAllAuthorsByName(String name);
    Optional<Author> getById(Long id);
    long countAuthors();
}
