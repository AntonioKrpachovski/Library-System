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

    @Override
    public Book AddBook(BookAddDTO bookInfo) {

        LocalDateTime creation = LocalDateTime.now();
        String ISBN = "filler";
        Book book = new Book(bookInfo, ISBN, creation, creation, new ArrayList<>());

        bookRepository.save(book);

        return book;
    }

    @Override
    public List<Book> GetBooksByAuthor(Author author) {
        return bookRepository.findByAuthors_id(author.getId());
    }

    @Override
    public List<Book> GetBooksByCategory(Category category) {
        return bookRepository.findByCategory_id(category.getId());
    }

    @Override
    public List<Book> ViewAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book EditBook(Long id) {

        Book book = bookRepository.findByIdNotNull(id).get();

        return null;
    }

    @Override
    public BookDTO BookDetails(Long id) {
        return null;
    }

    @Override
    public Book DeactivateBook(Long id) {

        Book book = bookRepository.findByIdNotNull(id).get();

        book.setActive(false);

        bookRepository.save(book);

        return book;
    }

    @Override
    public Book ReactivateBook(Long id) {

        Book book = bookRepository.findByIdNotNull(id).get();

        book.setActive(true);

        bookRepository.save(book);

        return book;
    }

    @Override
    public List<BookCopy> ViewPhysicalCopies(Book book) {
        return List.of();
    }

    @Override
    public long CountBooks() {
        return bookRepository.count();
    }

    @Override
    public Book FindBookById(Long id) {
        return bookRepository.findById(id).get();
    }
}
