package library.management.librarymanagement.web;

import library.management.librarymanagement.model.User;
import library.management.librarymanagement.model.dtos.BookAddDTO;
import library.management.librarymanagement.model.dtos.UserDTO;
import library.management.librarymanagement.repository.UserRepository;
import library.management.librarymanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMINISTRATOR') or #id == authentication.principal.id")
    @GetMapping("/users/{id}")
    public String usersDetailView(Model model, @PathVariable Long id){

        model.addAttribute("user",userService.findUserById(id));

        return "user-detail";
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/users")
    public String usersView(Model model){

        model.addAttribute("users",userService.getAllUsers());

        return "users";
    }

    @GetMapping("/users/new")
    public String addUserForm(Model model){

        model.addAttribute("user", new UserDTO());

        return "user-form";
    }

    @PostMapping("/users/new")
    public String addUser(@ModelAttribute("user") UserDTO userDTO, RedirectAttributes redirectAttributes){

        User user = userService.addUser(userDTO);

        redirectAttributes.addFlashAttribute("successMessage", "User added successfully.");

        return "redirect:/dashboard";
    }


    @PreAuthorize("hasRole('ADMINISTRATOR') or #id == authentication.principal.id")
    @GetMapping("/users/edit/{id}")
    public String editUserForm(Model model, @PathVariable Long id){

        User user = userService.findUserById(id);

        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setActiveStatus(user.getActiveStatus());

        model.addAttribute("user", userDTO);
        model.addAttribute("userId", id);

        return "user-form";
    }


    @PreAuthorize("hasRole('ADMINISTRATOR') or #id == authentication.principal.id")
    @PostMapping("/users/edit/{id}")
    public String editUser(@ModelAttribute("user") UserDTO userDTO, RedirectAttributes redirectAttributes, @PathVariable Long id) {

        userService.editUser(id, userDTO);

        redirectAttributes.addFlashAttribute("successMessage", "User successfully updated");

        return "redirect:/users/" + id;
    }
}
