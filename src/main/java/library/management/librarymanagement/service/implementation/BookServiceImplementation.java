package library.management.librarymanagement.service.implementation;

import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.Book;
import library.management.librarymanagement.model.BookCopy;
import library.management.librarymanagement.model.Category;
import library.management.librarymanagement.model.dtos.BookAddDTO;
import library.management.librarymanagement.model.dtos.BookDTO;
import library.management.librarymanagement.model.enums.BookStatus;
import library.management.librarymanagement.repository.BookRepository;
import library.management.librarymanagement.service.AuthorService;
import library.management.librarymanagement.service.BookService;
import library.management.librarymanagement.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Override
    public Book addBook(BookAddDTO bookInfo) {

        LocalDateTime creation = LocalDateTime.now();
        String ISBN = "filler";

        if (bookInfo.getCategoryId() != null) {
            bookInfo.setCategory(categoryService.getById(bookInfo.getCategoryId()).orElse(null));
        }

        List<Author> authors = resolveAuthors(bookInfo.getAuthorIds());

        Book book = new Book(bookInfo, ISBN, creation, creation, authors);

        return bookRepository.save(book);
    }

    private List<Author> resolveAuthors(List<Long> authorIds) {
        List<Author> authors = new ArrayList<>();

        if (authorIds != null) {
            for (Long authorId : authorIds) {
                authorService.getById(authorId).ifPresent(authors::add);
            }
        }

        return authors;
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.findByAuthors_id(author.getId());
    }

    @Override
    public List<Book> getBooksByCategory(Category category) {
        return bookRepository.findByCategory_id(category.getId());
    }

    @Override
    public List<Book> viewAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book editBook(Long id, BookAddDTO bookInfo) {

        Book book = bookRepository.findById(id).orElseThrow();

        book.setTitle(bookInfo.getTitle());
        book.setDescription(bookInfo.getDescription());
        book.setPublisher(bookInfo.getPublisher());
        book.setPublicationYear(bookInfo.getPublicationYear());
        book.setLanguage(bookInfo.getLanguage());
        book.setNumberOfPages(bookInfo.getNumberOfPages());
        book.setStatus(bookInfo.getStatus());
        book.setActive(bookInfo.isActive());

        if (bookInfo.getCategoryId() != null) {
            book.setCategory(categoryService.getById(bookInfo.getCategoryId()).orElse(book.getCategory()));
        }

        book.setAuthors(resolveAuthors(bookInfo.getAuthorIds()));

        return bookRepository.save(book);
    }

    @Override
    public BookDTO bookDetails(Long id) {
        return null;
    }

    @Override
    public Book deactivateBook(Long id) {

        Book book = bookRepository.findById(id).orElseThrow();

        book.setActive(false);

        return bookRepository.save(book);
    }

    @Override
    public Book reactivateBook(Long id) {

        Book book = bookRepository.findById(id).orElseThrow();

        book.setActive(true);

        return bookRepository.save(book);
    }

    @Override
    public List<BookCopy> viewPhysicalCopies(Book book) {
        return List.of();
    }

    @Override
    public long countBooks() {
        return bookRepository.count();
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).get();
    }
}
