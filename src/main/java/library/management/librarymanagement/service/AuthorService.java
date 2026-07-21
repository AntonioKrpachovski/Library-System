package library.management.librarymanagement.service;

import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.dtos.AuthorDTO;

import java.util.List;

public interface AuthorService {

    Author AddAuthor(AuthorDTO authorInfo);
    List<Author> AllAuthors();
    Author EditAuthor(Author author);
    List<Author> AllAuthorsByName(String name);

}
