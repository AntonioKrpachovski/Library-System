package library.management.librarymanagement.model;

import jakarta.persistence.*;
import library.management.librarymanagement.model.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import library.management.librarymanagement.model.Book;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String inventoryNumber;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book parentBook;
    @Enumerated(EnumType.STRING)
    private BookStatus currentStatus;
    private String shelfLocation;
    private LocalDateTime acquisitionDate;
    private String conditionNotes;

    public BookCopy(Book parentBook, String inventoryNumber, BookStatus currentStatus, String shelfLocation, String conditionNotes, LocalDateTime acquisitionDate) {
        this.parentBook = parentBook;
        this.inventoryNumber = inventoryNumber;
        this.currentStatus = currentStatus;
        this.shelfLocation = shelfLocation;
        this.conditionNotes = conditionNotes;
        this.acquisitionDate = acquisitionDate;
    }
}
