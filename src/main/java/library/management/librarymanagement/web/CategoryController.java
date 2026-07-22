package library.management.librarymanagement.web;

import library.management.librarymanagement.model.Category;
import library.management.librarymanagement.model.dtos.CategoryDTO;
import library.management.librarymanagement.model.enums.CategoryType;
import library.management.librarymanagement.service.AuthorService;
import library.management.librarymanagement.service.BookService;
import library.management.librarymanagement.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class CategoryController {
    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String CategoriesView(Model model){

        model.addAttribute("categories",categoryService.GetAllCategories());

        return "categories";
    }


    @GetMapping("/categories/{id}/books")
    public String CategoryBooksView(@PathVariable Long id, Model model){

        Category category = categoryService.GetById(id).get();

        model.addAttribute("pageTitle", "Available books from category " + category.getCategoryType() + ":");
        model.addAttribute("Books",bookService.GetBooksByCategory(category));

        return "books";
    }


    @GetMapping("/categories/new")
    public String CategoryAddForm(Model model){

        model.addAttribute("category", new CategoryDTO());
        model.addAttribute("categoryTypes", CategoryType.values());

        return "category-form";
    }

    @PostMapping("/categories/new")
    public String AddCategory(@ModelAttribute("category") CategoryDTO categoryDTO, RedirectAttributes redirectAttributes){

        categoryService.AddCategory(categoryDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Category added successfully.");

        return "redirect:/categories";
    }

    @GetMapping("/categories/{id}/edit")
    public String CategoryEditForm(@PathVariable Long id, Model model){

        Category category = categoryService.GetById(id).get();

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setStatus(category.isStatus());
        categoryDTO.setCategoryType(category.getCategoryType());

        model.addAttribute("category", categoryDTO);
        model.addAttribute("categoryId", id);
        model.addAttribute("categoryTypes", CategoryType.values());

        return "category-form";
    }

    @PostMapping("/categories/{id}/edit")
    public String EditCategory(@PathVariable Long id, @ModelAttribute("category") CategoryDTO categoryDTO, RedirectAttributes redirectAttributes){

        categoryService.EditCategory(id, categoryDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully.");

        return "redirect:/categories";
    }
}
