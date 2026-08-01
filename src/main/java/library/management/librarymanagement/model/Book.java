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
    @Column(name = "id")
    private Long id;
    @Column(name = "isbn", unique = true)
    private String ISBN;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "publication_year")
    private Year publicationYear;
    @Column(name = "language")
    private String language;
    @Column(name = "number_of_pages")
    private Long numberOfPages;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private BookStatus status;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

    public Book(String ISBN, String title, String description, String publisher, Year publicationYear, String language, Long numberOfPages, Category category, List<Author> authors, BookStatus status, LocalDateTime creationDate, LocalDateTime lastUpdateDate, boolean active) {
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
        this.authors = authors;
    }

    @PrePersist
    public void onCreate() {
        creationDate = LocalDateTime.now();
        lastUpdateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        lastUpdateDate = LocalDateTime.now();
    }
}
