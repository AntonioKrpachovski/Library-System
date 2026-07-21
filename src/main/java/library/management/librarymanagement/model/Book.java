package library.management.librarymanagement.model;

import jakarta.persistence.*;
import library.management.librarymanagement.model.dtos.BookAddDTO;
import library.management.librarymanagement.model.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String ISBN;
    private String title;
    private String description;
    private String publisher;
    private Year publicationYear;
    private String language;
    private Long numberOfPages;
    private String category;
    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();
    @Enumerated(value = EnumType.STRING)
    private BookStatus status;
    private Boolean active;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    public Book(String ISBN, String title, String description, String publisher, Year publicationYear, String language, Long numberOfPages, String category, List<Author> authors, BookStatus status, LocalDateTime creationDate, LocalDateTime lastUpdateDate, boolean active) {
        this.ISBN = ISBN;
        this.title = title;
        this.description = description;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.language = language;
        this.numberOfPages = numberOfPages;
        this.category = category;
        this.authors = authors;
        this.status = status;
        this.active = active;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Book(BookAddDTO bookInfo, String ISBN, LocalDateTime lastUpdateDate, LocalDateTime creationDate, List<Author> authors) {

        this.ISBN = ISBN;
        this.title = bookInfo.getTitle();
        this.description = bookInfo.getDescription();
        this.publisher = bookInfo.getPublisher();
        this.publicationYear = bookInfo.getPublicationYear();
        this.language = bookInfo.getLanguage();
        this.numberOfPages = bookInfo.getNumberOfPages();
        this.category = bookInfo.getCategory();
        this.status = bookInfo.getStatus();
        this.active = bookInfo.isActive();
        this.lastUpdateDate = lastUpdateDate;
        this.creationDate = creationDate;
        this.authors = authors;
    }
}
