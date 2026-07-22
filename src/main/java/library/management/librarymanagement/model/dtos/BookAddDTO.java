package library.management.librarymanagement.model.dtos;

import library.management.librarymanagement.model.Category;
import library.management.librarymanagement.model.enums.BookStatus;
import lombok.Data;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookAddDTO {
    private String title;
    String description;
    private String publisher;
    private Year publicationYear;
    private String language;
    private Long numberOfPages;
    private Category category;
    private Long categoryId;
    private List<Long> authorIds = new ArrayList<>();
    private BookStatus status;
    private boolean active = true;
}
