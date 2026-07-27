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
    public Author addAuthor(AuthorDTO authorInfo) {
        Author author = new Author(authorInfo);

        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author editAuthor(Long id, AuthorDTO authorInfo) {

        Author author = authorRepository.findById(id).get();

        author.setFirstName(authorInfo.getFirstName());
        author.setLastName(authorInfo.getLastName());
        author.setOptionalBiography(authorInfo.getOptionalBiography());

        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthorsByName(String name) {
        return authorRepository.findAllByFirstName(name);
    }

    @Override
    public long countAuthors() {
        return authorRepository.count();
    }

    @Override
    public Optional<Author> getById(Long id) {
        return authorRepository.findById(id);
    }
}
