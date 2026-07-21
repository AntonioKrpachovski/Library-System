package library.management.librarymanagement.model.dtos;

import lombok.Data;

@Data
public class AuthorDTO {
    private String firstName;
    private String lastName;
    private String optionalBiography;
}
