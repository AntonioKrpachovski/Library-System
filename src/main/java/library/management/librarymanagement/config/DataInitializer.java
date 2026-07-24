package library.management.librarymanagement.config;

import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.Book;
import library.management.librarymanagement.model.Category;
import library.management.librarymanagement.model.User;
import library.management.librarymanagement.model.enums.BookStatus;
import library.management.librarymanagement.model.enums.UserRole;
import library.management.librarymanagement.repository.AuthorRepository;
import library.management.librarymanagement.repository.BookRepository;
import library.management.librarymanagement.repository.CategoryRepository;
import library.management.librarymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private static final String DEFAULT_ADMIN_USERNAME = "admin";
    private static final String DEFAULT_ADMIN_PASSWORD = "admin";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername(DEFAULT_ADMIN_USERNAME).isPresent()) {
            return;
        }

        User admin = new User(
                DEFAULT_ADMIN_USERNAME,
                passwordEncoder.encode(DEFAULT_ADMIN_PASSWORD),
                "System",
                "Administrator",
                "admin@library.local",
                UserRole.Administrator,
                true,
                LocalDateTime.now()
        );

        userRepository.save(admin);

        User user = new User(
                "antonio",
                passwordEncoder.encode("antonio"),
                "Antonio",
                "Krpachovski",
                "email@email.com",
                UserRole.Member,
                true,
                LocalDateTime.now()
        );

        userRepository.save(user);

        Author author1 = new Author(
                "George",
                "Orwell",
                "English novelist and essayist, known for dystopian works."
        );

        author1 = authorRepository.save(author1);


        Author author2 = new Author(
                "J.K.",
                "Rowling",
                "British author best known for the Harry Potter series."
        );

        author2 = authorRepository.save(author2);


        Category scienceFiction = new Category(
                "Science Fiction",
                "Books about futuristic concepts, technology, and imaginary worlds.",
                true
        );

        scienceFiction = categoryRepository.save(scienceFiction);


        Category children = new Category(
                "Children",
                "Books written for children and young readers.",
                true
        );

        children = categoryRepository.save(children);


        Category programming = new Category(
                "Programming",
                "Books about software development and computer science.",
                true
        );

        programming = categoryRepository.save(programming);


        Book book1 = new Book(
                "9780451524935",
                "1984",
                "A dystopian novel about a totalitarian society.",
                "Secker & Warburg",
                Year.of(1949),
                "English",
                328L,
                children,
                List.of(author1),
                BookStatus.AVAILABLE,
                LocalDateTime.now(),
                LocalDateTime.now(),
                true
        );

        Book book2 = new Book(
                "9780747532743",
                "Harry Potter and the Philosopher's Stone",
                "A young wizard discovers his magical heritage.",
                "Bloomsbury",
                Year.of(1997),
                "English",
                223L,
                programming,
                List.of(author1, author2),
                BookStatus.AVAILABLE,
                LocalDateTime.now(),
                LocalDateTime.now(),
                true
        );

        bookRepository.saveAll(List.of(book1, book2));
    }
}
