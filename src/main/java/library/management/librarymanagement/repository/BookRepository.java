package library.management.librarymanagement.repository;

import library.management.librarymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<User, Long>{

}
