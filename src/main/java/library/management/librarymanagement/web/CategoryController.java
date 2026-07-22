package library.management.librarymanagement.web;

import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.Category;
import library.management.librarymanagement.service.AuthorService;
import library.management.librarymanagement.service.BookService;
import library.management.librarymanagement.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String CategoryAddForm(){
        return "category-form";
    }

    @PostMapping("/categories/new")
    public String AddCategory(){
        return "redirect:/categories";
    }

    @GetMapping("/categories/{id}/edit")
    public String CategoryEditForm(@PathVariable Long id, Model model){
        return "category-form";
    }

    @PostMapping("/categories/{id}/edit")
    public String EditCategory(@PathVariable Long id, Model model){
        return "redirect:/categories";
    }
}
