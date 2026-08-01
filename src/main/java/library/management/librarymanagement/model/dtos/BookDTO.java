package library.management.librarymanagement.model.dtos;

import library.management.librarymanagement.model.enums.BookStatus;

import java.time.LocalDateTime;

public class BookDTO {
    private Long inventoryNumber;
    private BookStatus status;
    private String location;
    private LocalDateTime date;
    private String notes;
    private String actions;
}
