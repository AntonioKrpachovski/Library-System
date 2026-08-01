package library.management.librarymanagement.web;

import jakarta.validation.Valid;
import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.dtos.AuthorDTO;
import library.management.librarymanagement.service.AuthorService;
import library.management.librarymanagement.service.BookService;
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
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;

    @GetMapping("/authors")
    public String authorsView(Model model){

        model.addAttribute("authors",authorService.getAllAuthors());

        return "authors";
    }

    @GetMapping("/authors/{id}")
    public String authorDetailsView(@PathVariable Long id, Model model){

        model.addAttribute("author", authorService.getById(id).get());

        return "author-detail";
    }

    @GetMapping("/authors/{id}/books")
    public String authorBooksView(@PathVariable Long id, Model model){

        Author author = authorService.getById(id).get();

        model.addAttribute("pageTitle", "Available books from author " + author.getFirstName() + " " + author.getLastName() + ":");
        model.addAttribute("Books",bookService.getBooksByAuthor(author));

        return "books";
    }


    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/authors/new")
    public String authorAddForm(Model model){

        model.addAttribute("author", new AuthorDTO());

        return "author-form";
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/authors/new")
    public String addAuthor(@Valid @ModelAttribute("author") AuthorDTO authorDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            return "author-form";
        }

        Author author = authorService.addAuthor(authorDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Author added successfully.");

        return "redirect:/authors/" + author.getId();
    }


    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/authors/{id}/edit")
    public String authorEditForm(@PathVariable Long id, Model model){

        Author author = authorService.getById(id).get();

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        authorDTO.setOptionalBiography(author.getOptionalBiography());

        model.addAttribute("author", authorDTO);
        model.addAttribute("authorId", id);

        return "author-form";
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/authors/{id}/edit")
    public String editAuthor(@PathVariable Long id, @Valid @ModelAttribute("author") AuthorDTO authorDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            model.addAttribute("authorId", id);
            return "author-form";
        }

        authorService.editAuthor(id, authorDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Author updated successfully.");

        return "redirect:/authors/" + id;
    }
}
