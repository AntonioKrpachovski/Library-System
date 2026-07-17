package library.management.librarymanagement.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
