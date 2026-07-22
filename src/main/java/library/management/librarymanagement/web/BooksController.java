package library.management.librarymanagement.web;

import library.management.librarymanagement.model.Author;
import library.management.librarymanagement.model.Book;
import library.management.librarymanagement.model.dtos.BookAddDTO;
import library.management.librarymanagement.model.enums.BookStatus;
import library.management.librarymanagement.service.AuthorService;
import library.management.librarymanagement.service.BookService;
import library.management.librarymanagement.service.CategoryService;
import lombok.AllArgsConstructor;
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
    public String BooksView(Model model){

        model.addAttribute("pageTitle", "Available books:");
        model.addAttribute("Books",bookService.ViewAllBooks());

        return "books";
    }

    @GetMapping("/members")
    public String MembersView(){
        return "members";
    }

    @GetMapping("/loans")
    public String LoansView(){
        return "loans";
    }

    @GetMapping("/administration")
    public String AdministrationView(){
        return "administration";
    }

    @GetMapping("/books/{id}")
    public String BookDetailsView(@PathVariable Long id, Model model){

        model.addAttribute("book", bookService.FindBookById(id));

        return "book-detail";
    }

    @GetMapping("/books/new")
    public String BookAddForm(Model model){

        model.addAttribute("book", new BookAddDTO());
        model.addAttribute("categories", categoryService.GetAllCategories());
        model.addAttribute("authors", authorService.GetAllAuthors());
        model.addAttribute("statuses", BookStatus.values());

        return "book-form";
    }

    @PostMapping("/books/new")
    public String AddBook(@ModelAttribute("book") BookAddDTO bookDTO, RedirectAttributes redirectAttributes){

        Book book = bookService.AddBook(bookDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Book added successfully.");

        return "redirect:/books/" + book.getId();
    }

    @GetMapping("/books/{id}/edit")
    public String BookEditForm(@PathVariable Long id, Model model){

        Book book = bookService.FindBookById(id);

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
        model.addAttribute("categories", categoryService.GetAllCategories());
        model.addAttribute("authors", authorService.GetAllAuthors());
        model.addAttribute("statuses", BookStatus.values());

        return "book-form";
    }

    @PostMapping("/books/{id}/edit")
    public String EditBook(@PathVariable Long id, @ModelAttribute("book") BookAddDTO bookDTO, RedirectAttributes redirectAttributes){

        bookService.EditBook(id, bookDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully.");

        return "redirect:/books/" + id;
    }

}
