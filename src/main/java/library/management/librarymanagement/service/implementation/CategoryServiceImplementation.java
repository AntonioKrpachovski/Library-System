package library.management.librarymanagement.service.implementation;

import library.management.librarymanagement.model.Category;
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
}
