package library.management.librarymanagement.web;

import library.management.librarymanagement.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class BooksController {

    private final BookService bookService;

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
    public String BookAddForm(){
        return "book-form";
    }

    @PostMapping("/books/new")
    public String AddBook(){
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/edit")
    public String BookEditForm(@PathVariable Long id, Model model){
        return "book-form";
    }

    @PostMapping("/books/{id}/edit")
    public String EditBook(@PathVariable Long id, Model model){
        return "redirect:/books";
    }

}
