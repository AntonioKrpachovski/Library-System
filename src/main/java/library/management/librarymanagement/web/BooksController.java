package library.management.librarymanagement.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BooksController {

    @GetMapping("/dashboard")
    public String DashboardView(){
        return "index";
    }
}
