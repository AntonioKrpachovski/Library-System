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
    public BookCopy addCopy(Long bookId, BookCopyDTO copyInfo) {

        Book book = bookService.findBookById(bookId);

        BookCopy copy = new BookCopy(
                book,
                copyInfo.getInventoryNumber(),
                copyInfo.getCurrentStatus(),
                copyInfo.getShelfLocation(),
                copyInfo.getConditionNotes(),
                copyInfo.getAcquisitionDate()
        );

        return bookCopyRepository.save(copy);
    }

    @Override
    public BookCopy editCopy(Long id, BookCopyDTO copyInfo) {

        BookCopy copy = bookCopyRepository.findById(id).orElseThrow();

        copy.setInventoryNumber(copyInfo.getInventoryNumber());
        copy.setCurrentStatus(copyInfo.getCurrentStatus());
        copy.setShelfLocation(copyInfo.getShelfLocation());
        copy.setConditionNotes(copyInfo.getConditionNotes());
        copy.setAcquisitionDate(copyInfo.getAcquisitionDate());

        return bookCopyRepository.save(copy);
    }

    @Override
    public Optional<BookCopy> getById(Long id) {
        return bookCopyRepository.findById(id);
    }

    @Override
    public List<BookCopy> getCopiesByBook(Long bookId) {
        return bookCopyRepository.findByParentBook_Id(bookId);
    }
}
