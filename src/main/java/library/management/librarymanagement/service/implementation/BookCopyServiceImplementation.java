package library.management.librarymanagement.service.implementation;

import library.management.librarymanagement.model.Book;
import library.management.librarymanagement.model.BookCopy;
import library.management.librarymanagement.model.dtos.BookCopyDTO;
import library.management.librarymanagement.repository.BookCopyRepository;
import library.management.librarymanagement.service.BookCopyService;
import library.management.librarymanagement.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookCopyServiceImplementation implements BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    private final BookService bookService;

    @Override
    public BookCopy AddCopy(Long bookId, BookCopyDTO copyInfo) {

        Book book = bookService.FindBookById(bookId);

        BookCopy copy = new BookCopy(
                book,
                copyInfo.getInventoryNumber(),
                copyInfo.getCurrentStatus(),
                copyInfo.getShelfLocation(),
                copyInfo.getConditionNotes(),
                copyInfo.getAcquisitionDate()
        );

        bookCopyRepository.save(copy);

        return copy;
    }

    @Override
    public BookCopy EditCopy(Long id, BookCopyDTO copyInfo) {

        BookCopy copy = bookCopyRepository.findById(id).get();

        copy.setInventoryNumber(copyInfo.getInventoryNumber());
        copy.setCurrentStatus(copyInfo.getCurrentStatus());
        copy.setShelfLocation(copyInfo.getShelfLocation());
        copy.setConditionNotes(copyInfo.getConditionNotes());
        copy.setAcquisitionDate(copyInfo.getAcquisitionDate());

        bookCopyRepository.save(copy);

        return copy;
    }

    @Override
    public Optional<BookCopy> GetById(Long id) {
        return bookCopyRepository.findById(id);
    }

    @Override
    public List<BookCopy> GetCopiesByBook(Long bookId) {
        return bookCopyRepository.findByParentBook_Id(bookId);
    }
}
