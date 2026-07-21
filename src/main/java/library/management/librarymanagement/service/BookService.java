package library.management.librarymanagement.service;
import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.Book;
import library.management.librarymanagement.model.BookCopy;
import library.management.librarymanagement.model.dtos.BookAddDTO;
import library.management.librarymanagement.model.dtos.BookDTO;

import java.util.List;

public interface BookService {
    Book AddBook(BookAddDTO bookInfo);

    List<Book> ViewBooksByAuthor(Author author);

    List<Book> ViewAllBooks();

    Book EditBook(Long id);

    BookDTO BookDetails(Long id);

    Book DeactivateBook(Long id);

    Book ReactivateBook(Long id);

    List<BookCopy> ViewPhysicalCopies(Book book);

}
