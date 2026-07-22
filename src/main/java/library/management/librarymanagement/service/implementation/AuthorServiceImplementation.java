package library.management.librarymanagement.service.implementation;

import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.dtos.AuthorDTO;
import library.management.librarymanagement.repository.AuthorRepository;
import library.management.librarymanagement.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Author> GetAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author EditAuthor(Long id, AuthorDTO authorInfo) {

        Author author = authorRepository.findById(id).get();

        author.setFirstName(authorInfo.getFirstName());
        author.setLastName(authorInfo.getLastName());
        author.setOptionalBiography(authorInfo.getOptionalBiography());

        authorRepository.save(author);

        return author;
    }

    @Override
    public List<Author> GetAllAuthorsByName(String name) {
        return authorRepository.findAllByFirstName(name);
    }

    @Override
    public long CountAuthors() {
        return authorRepository.count();
    }

    @Override
    public Optional<Author> GetById(Long id) {
        return authorRepository.findById(id);
    }
}
