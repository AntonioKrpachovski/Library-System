package library.management.librarymanagement.service;
import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.Book;
import library.management.librarymanagement.model.BookCopy;
import library.management.librarymanagement.model.Category;
import library.management.librarymanagement.model.dtos.BookAddDTO;
import library.management.librarymanagement.model.dtos.BookDTO;

import java.util.List;

public interface BookService {
    Book addBook(BookAddDTO bookInfo);

    Book findBookById(Long id);

    List<Book> getBooksByAuthor(Author author);

    List<Book> getBooksByCategory(Category category);

    List<Book> viewAllBooks();

    Book editBook(Long id, BookAddDTO bookInfo);

    BookDTO bookDetails(Long id);

    Book deactivateBook(Long id);

    Book reactivateBook(Long id);

    List<BookCopy> viewPhysicalCopies(Book book);

    long countBooks();
}
