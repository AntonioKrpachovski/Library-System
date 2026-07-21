package library.management.librarymanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import library.management.librarymanagement.model.enums.BookStatus;

import java.awt.print.Book;
import java.util.Date;

public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String inventoryNumber;
    private Book parentBook;
    private BookStatus currentStatus;
    private String shelfLocation;
    private Date acquisitionDate;
    private String conditionNotes;
}
