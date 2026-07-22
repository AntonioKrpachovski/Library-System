package library.management.librarymanagement.repository;

import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByFirstName(String name);
    Optional<Author> findById(Long id);
}
