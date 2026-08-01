package library.management.librarymanagement.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import library.management.librarymanagement.model.Category;
import library.management.librarymanagement.model.enums.BookStatus;
import lombok.Data;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookAddDTO {
    @NotBlank
    private String title;
    String description;
    private String publisher;
    @NotNull
    private Year publicationYear;
    @NotBlank
    private String language;
    private Long numberOfPages;
    private Category category;
    @NotNull
    private Long categoryId;
    private List<Long> authorIds = new ArrayList<>();
    private BookStatus status;
    private boolean active = true;
}
