package library.management.librarymanagement.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BooksController {

    @GetMapping("/dashboard")
    public String DashboardView(){
        return "index";
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


}
