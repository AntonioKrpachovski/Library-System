package library.management.librarymanagement.web;

import library.management.librarymanagement.model.User;
import library.management.librarymanagement.model.dtos.BookAddDTO;
import library.management.librarymanagement.model.dtos.UserDTO;
import library.management.librarymanagement.repository.UserRepository;
import library.management.librarymanagement.service.UserService;
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
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public String usersDetailView(Model model, @PathVariable Long id){

        model.addAttribute("user",userService.findUserById(id));

        return "user-detail";
    }

    @GetMapping("/users")
    public String usersView(Model model){

        model.addAttribute("users",userService.getAllUsers());

        return "users";
    }

    @GetMapping("/users/new")
    public String addUserForm(Model model){
        return "user-form";
    }

    @PostMapping("/users/new")
    public String addUser(@ModelAttribute("user") UserDTO userDTO, RedirectAttributes redirectAttributes){

        return "redirect:/users/";
    }
}
