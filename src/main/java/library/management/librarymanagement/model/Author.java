package library.management.librarymanagement.model;

import jakarta.persistence.*;
import library.management.librarymanagement.model.dtos.AuthorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authors")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String optionalBiography;

    public Author(String firstName, String lastName, String optionalBiography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.optionalBiography = optionalBiography;
    }

    public Author(AuthorDTO authorInfo) {
        this.firstName = authorInfo.getFirstName();
        this.lastName = authorInfo.getLastName();
        this.optionalBiography = authorInfo.getOptionalBiography();
    }
}
