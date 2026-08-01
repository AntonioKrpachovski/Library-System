package library.management.librarymanagement.model.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {
    @NotBlank
    private String name;
    private String description;
    private boolean status;
}
