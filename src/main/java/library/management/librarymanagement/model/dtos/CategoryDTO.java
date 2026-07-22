package library.management.librarymanagement.model.dtos;

import library.management.librarymanagement.model.enums.CategoryType;
import lombok.Data;

@Data
public class CategoryDTO {
    private String name;
    private String description;
    private boolean status;
    private CategoryType categoryType;
}
