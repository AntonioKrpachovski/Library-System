package library.management.librarymanagement.service;

import library.management.librarymanagement.model.BookCopy;
import library.management.librarymanagement.model.dtos.BookCopyDTO;

import java.util.List;
import java.util.Optional;

public interface BookCopyService {
    BookCopy addCopy(Long bookId, BookCopyDTO copyInfo);
    BookCopy editCopy(Long id, BookCopyDTO copyInfo);
    Optional<BookCopy> getById(Long id);
    List<BookCopy> getCopiesByBook(Long bookId);
}
