package library.management.librarymanagement.model.dtos;

import lombok.Data;

@Data
public class CategoryDTO {
    private String name;
    private String description;
    private boolean status;
}
