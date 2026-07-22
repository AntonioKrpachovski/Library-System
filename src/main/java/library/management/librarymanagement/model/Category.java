package library.management.librarymanagement.model;

import jakarta.persistence.*;
import library.management.librarymanagement.model.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean status;
    @Enumerated(value = EnumType.STRING)
    private CategoryType categoryType;

    public Category(String name, String description, boolean status, CategoryType categoryType) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.categoryType = categoryType;
    }
}
