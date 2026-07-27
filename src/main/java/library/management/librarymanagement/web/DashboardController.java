package library.management.librarymanagement.web;

import library.management.librarymanagement.service.AuthorService;
import library.management.librarymanagement.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DashboardController {

    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping({"/", "/dashboard"})
    public String dashboardView(Model model){

        model.addAttribute("BookCount", bookService.countBooks());
        model.addAttribute("AuthorCount", authorService.countAuthors());
        return "dashboard";
    }
}
