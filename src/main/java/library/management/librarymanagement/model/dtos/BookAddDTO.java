package library.management.librarymanagement.model.dtos;

import library.management.librarymanagement.model.Category;
import library.management.librarymanagement.model.enums.BookStatus;
import lombok.Data;

import java.time.Year;

@Data
public class BookAddDTO {
    private String title;
    String description;
    private String publisher;
    private Year publicationYear;
    private String language;
    private Long numberOfPages;
    private Category category;
    private BookStatus status;
    private boolean active;
}
