package library.management.librarymanagement.service.implementation;

import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.dtos.AuthorDTO;
import library.management.librarymanagement.repository.AuthorRepository;
import library.management.librarymanagement.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImplementation implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author AddAuthor(AuthorDTO authorInfo) {
        Author author = new Author(authorInfo);

        authorRepository.save(author);
        return author;
    }

    @Override
    public List<Author> AllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author EditAuthor(Author author) {

        authorRepository.findById(author.getId());

        return author;
    }

    @Override
    public List<Author> AllAuthorsByName(String name) {
        return authorRepository.findAllByName(name);
    }
}
