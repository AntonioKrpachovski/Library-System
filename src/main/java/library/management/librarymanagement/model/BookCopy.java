package library.management.librarymanagement.model;

import jakarta.persistence.*;
import library.management.librarymanagement.model.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_copy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "inventory_number", unique = true)
    private String inventoryNumber;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book parentBook;
    @Enumerated(EnumType.STRING)
    @Column(name = "current_status")
    private BookStatus currentStatus;
    @Column(name = "shelf_location")
    private String shelfLocation;
    @Column(name = "acquisition_date")
    private LocalDateTime acquisitionDate;
    @Column(name = "condition_notes")
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
