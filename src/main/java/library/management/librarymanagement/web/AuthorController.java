package library.management.librarymanagement.web;

import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.dtos.AuthorDTO;
import library.management.librarymanagement.service.AuthorService;
import library.management.librarymanagement.service.BookService;
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
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;

    @GetMapping("/authors")
    public String AuthorsView(Model model){

        model.addAttribute("authors",authorService.GetAllAuthors());

        return "authors";
    }

    @GetMapping("/authors/{id}")
    public String AuthorDetailsView(@PathVariable Long id, Model model){

        model.addAttribute("author", authorService.GetById(id).get());

        return "author-detail";
    }

    @GetMapping("/authors/{id}/books")
    public String AuthorBooksView(@PathVariable Long id, Model model){

        Author author = authorService.GetById(id).get();

        model.addAttribute("pageTitle", "Available books from author " + author.getFirstName() + " " + author.getLastName() + ":");
        model.addAttribute("Books",bookService.GetBooksByAuthor(author));

        return "books";
    }


    @GetMapping("/authors/new")
    public String AuthorAddForm(Model model){

        model.addAttribute("author", new AuthorDTO());

        return "author-form";
    }

    @PostMapping("/authors/new")
    public String AddAuthor(@ModelAttribute("author") AuthorDTO authorDTO, RedirectAttributes redirectAttributes){

        Author author = authorService.AddAuthor(authorDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Author added successfully.");

        return "redirect:/authors/" + author.getId();
    }


    @GetMapping("/authors/{id}/edit")
    public String AuthorEditForm(@PathVariable Long id, Model model){

        Author author = authorService.GetById(id).get();

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        authorDTO.setOptionalBiography(author.getOptionalBiography());

        model.addAttribute("author", authorDTO);
        model.addAttribute("authorId", id);

        return "author-form";
    }

    @PostMapping("/authors/{id}/edit")
    public String EditAuthor(@PathVariable Long id, @ModelAttribute("author") AuthorDTO authorDTO, RedirectAttributes redirectAttributes){

        authorService.EditAuthor(id, authorDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Author updated successfully.");

        return "redirect:/authors/" + id;
    }
}
