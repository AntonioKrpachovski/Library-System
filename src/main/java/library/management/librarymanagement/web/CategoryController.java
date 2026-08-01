package library.management.librarymanagement.web;

import jakarta.validation.Valid;
import library.management.librarymanagement.model.Category;
import library.management.librarymanagement.model.dtos.CategoryDTO;
import library.management.librarymanagement.service.AuthorService;
import library.management.librarymanagement.service.BookService;
import library.management.librarymanagement.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String categoriesView(Model model){

        model.addAttribute("categories",categoryService.getAllCategories());

        return "categories";
    }


    @GetMapping("/categories/{id}/books")
    public String categoryBooksView(@PathVariable Long id, Model model){

        Category category = categoryService.getById(id).get();

        model.addAttribute("pageTitle", "Available books from category " + category.getName() + ":");
        model.addAttribute("Books",bookService.getBooksByCategory(category));

        return "books";
    }


    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/categories/new")
    public String categoryAddForm(Model model){

        model.addAttribute("category", new CategoryDTO());
        return "category-form";
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/categories/new")
    public String addCategory(@Valid @ModelAttribute("category") CategoryDTO categoryDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            return "category-form";
        }

        categoryService.addCategory(categoryDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Category added successfully.");

        return "redirect:/categories";
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/categories/{id}/edit")
    public String categoryEditForm(@PathVariable Long id, Model model){

        Category category = categoryService.getById(id).get();

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setStatus(category.isStatus());

        model.addAttribute("category", categoryDTO);
        model.addAttribute("categoryId", id);

        return "category-form";
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/categories/{id}/edit")
    public String editCategory(@PathVariable Long id, @Valid @ModelAttribute("category") CategoryDTO categoryDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            model.addAttribute("categoryId", id);
            return "category-form";
        }

        categoryService.editCategory(id, categoryDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully.");

        return "redirect:/categories";
    }
}
