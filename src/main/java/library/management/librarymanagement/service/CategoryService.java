package library.management.librarymanagement.service;

import library.management.librarymanagement.model.Category;
import library.management.librarymanagement.model.dtos.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> getById(Long id);
    List<Category> getAllCategories();
    Category addCategory(CategoryDTO categoryInfo);
    Category editCategory(Long id, CategoryDTO categoryInfo);
}
