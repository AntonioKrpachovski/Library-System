package library.management.librarymanagement.model;

import jakarta.persistence.*;
import library.management.librarymanagement.model.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long ISBN;
    private String title;
    private String description;
    private String publisher;
    private Year publicationYear;
    private String language;
    private Long numberOfPages;
    private String category;
    private List<Author> authors;
    @Enumerated(value = EnumType.STRING)
    private BookStatus status;
    private Date creationDate;
    private String lastUpdateDate;
}
