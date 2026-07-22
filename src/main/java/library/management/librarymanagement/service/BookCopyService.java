package library.management.librarymanagement.service;

import library.management.librarymanagement.model.BookCopy;
import library.management.librarymanagement.model.dtos.BookCopyDTO;

import java.util.List;
import java.util.Optional;

public interface BookCopyService {
    BookCopy AddCopy(Long bookId, BookCopyDTO copyInfo);
    BookCopy EditCopy(Long id, BookCopyDTO copyInfo);
    Optional<BookCopy> GetById(Long id);
    List<BookCopy> GetCopiesByBook(Long bookId);
}
