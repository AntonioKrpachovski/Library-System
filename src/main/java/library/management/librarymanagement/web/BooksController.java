package library.management.librarymanagement.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BooksController {

    @GetMapping("/")
    public String DefaultView(){
        return "dashboard";
    }

    @GetMapping("/dashboard")
    public String DashboardView(){
        return "dashboard";
    }

    @GetMapping("/books")
    public String BooksView(){
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
    public String AdministrationView(long id){
        return "book-detail";
    }

    @PostMapping("/books/new")
    public String AddBook(){
        return "redirect:/books";
    }

    @PostMapping("/authors/new")
    public String AddAuthor(){
        return "redirect:/dashboard";
    }
}
