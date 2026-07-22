package library.management.librarymanagement.repository;

import library.management.librarymanagement.model.Book;
import library.management.librarymanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
