package library.management.librarymanagement.model.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String optionalBiography;
}
