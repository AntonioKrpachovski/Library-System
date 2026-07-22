package library.management.librarymanagement.service;

import library.management.librarymanagement.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> GetById(Long id);
    List<Category> GetAllCategories();
}
