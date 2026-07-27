package library.management.librarymanagement.web;

import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.Book;
import library.management.librarymanagement.model.dtos.BookAddDTO;
import library.management.librarymanagement.model.enums.BookStatus;
import library.management.librarymanagement.service.AuthorService;
import library.management.librarymanagement.service.BookService;
import library.management.librarymanagement.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class BooksController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Year.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text != null && !text.isBlank() ? Year.parse(text) : null);
            }

            @Override
            public String getAsText() {
                Year value = (Year) getValue();
                return value != null ? value.toString() : "";
            }
        });
    }

    @GetMapping("/books")
    public String booksView(Model model){

        model.addAttribute("pageTitle", "Available books:");
        model.addAttribute("Books",bookService.viewAllBooks());

        return "books";
    }

    @GetMapping("/members")
    public String membersView(){
        return "members";
    }

    @GetMapping("/loans")
    public String loansView(){
        return "loans";
    }

    @GetMapping("/administration")
    public String administrationView(){
        return "administration";
    }

    @GetMapping("/books/{id}")
    public String bookDetailsView(@PathVariable Long id, Model model){

        model.addAttribute("book", bookService.findBookById(id));

        return "book-detail";
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/books/new")
    public String bookAddForm(Model model){

        model.addAttribute("book", new BookAddDTO());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("statuses", BookStatus.values());

        return "book-form";
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/books/new")
    public String addBook(@ModelAttribute("book") BookAddDTO bookDTO, RedirectAttributes redirectAttributes){

        Book book = bookService.addBook(bookDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Book added successfully.");

        return "redirect:/books/" + book.getId();
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/books/{id}/edit")
    public String bookEditForm(@PathVariable Long id, Model model){

        Book book = bookService.findBookById(id);

        BookAddDTO bookDTO = new BookAddDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setPublicationYear(book.getPublicationYear());
        bookDTO.setLanguage(book.getLanguage());
        bookDTO.setNumberOfPages(book.getNumberOfPages());
        bookDTO.setCategoryId(book.getCategory() != null ? book.getCategory().getId() : null);
        bookDTO.setStatus(book.getStatus());
        bookDTO.setActive(Boolean.TRUE.equals(book.getActive()));
        bookDTO.setAuthorIds(book.getAuthors().stream().map(Author::getId).collect(Collectors.toList()));

        model.addAttribute("book", bookDTO);
        model.addAttribute("bookId", id);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("statuses", BookStatus.values());

        return "book-form";
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/books/{id}/edit")
    public String editBook(@PathVariable Long id, @ModelAttribute("book") BookAddDTO bookDTO, RedirectAttributes redirectAttributes){

        bookService.editBook(id, bookDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully.");

        return "redirect:/books/" + id;
    }

}
