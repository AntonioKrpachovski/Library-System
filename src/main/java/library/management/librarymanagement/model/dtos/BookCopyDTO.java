package library.management.librarymanagement.model.dtos;

import library.management.librarymanagement.model.enums.BookStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BookCopyDTO {
    private String inventoryNumber;
    private BookStatus currentStatus;
    private String shelfLocation;
    private LocalDateTime acquisitionDate;
    private String conditionNotes;
}
