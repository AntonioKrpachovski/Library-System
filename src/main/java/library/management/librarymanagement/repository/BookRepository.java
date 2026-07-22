package library.management.librarymanagement.repository;

import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.Book;
import library.management.librarymanagement.model.Category;
import library.management.librarymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    Optional<Book> findById(Long id);
    Optional<Book> findByIdNotNull(Long id);
    Optional<Book> findByISBN(String isbn);
    List<Book> findByCategory_id(Long id);
    List<Book> findByAuthors_id(Long id);
}
