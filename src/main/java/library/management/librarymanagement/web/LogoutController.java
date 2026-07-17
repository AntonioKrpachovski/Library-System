package library.management.librarymanagement.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate();
        return "redirect:/login";

    }
}
