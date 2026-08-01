package library.management.librarymanagement.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import library.management.librarymanagement.model.enums.BookStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookCopyDTO {
    @NotBlank
    private String inventoryNumber;
    @NotNull
    private BookStatus currentStatus;
    private String shelfLocation;
    private LocalDateTime acquisitionDate;
    private String conditionNotes;
}
