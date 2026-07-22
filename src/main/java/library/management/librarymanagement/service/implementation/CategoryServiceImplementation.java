package library.management.librarymanagement.service.implementation;

import library.management.librarymanagement.model.Category;
import library.management.librarymanagement.model.dtos.CategoryDTO;
import library.management.librarymanagement.repository.CategoryRepository;
import library.management.librarymanagement.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> GetById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> GetAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category AddCategory(CategoryDTO categoryInfo) {
        Category category = new Category(
                categoryInfo.getName(),
                categoryInfo.getDescription(),
                categoryInfo.isStatus(),
                categoryInfo.getCategoryType()
        );

        categoryRepository.save(category);

        return category;
    }

    @Override
    public Category EditCategory(Long id, CategoryDTO categoryInfo) {
        Category category = categoryRepository.findById(id).get();

        category.setName(categoryInfo.getName());
        category.setDescription(categoryInfo.getDescription());
        category.setStatus(categoryInfo.isStatus());
        category.setCategoryType(categoryInfo.getCategoryType());

        categoryRepository.save(category);

        return category;
    }
}
