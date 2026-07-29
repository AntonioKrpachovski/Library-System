package library.management.librarymanagement.web;

import library.management.librarymanagement.model.Member;
import library.management.librarymanagement.model.User;
import library.management.librarymanagement.model.dtos.BookAddDTO;
import library.management.librarymanagement.model.dtos.MemberDTO;
import library.management.librarymanagement.model.dtos.MemberEditDTO;
import library.management.librarymanagement.model.dtos.RegistrationDTO;
import library.management.librarymanagement.model.dtos.UserDTO;
import library.management.librarymanagement.repository.UserRepository;
import library.management.librarymanagement.service.MemberService;
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
    private final MemberService memberService;

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


    @GetMapping("/register")
    public String registerUserForm(Model model) {

        model.addAttribute("registration", new RegistrationDTO());

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("registration") RegistrationDTO registration,
            RedirectAttributes redirectAttributes) {

        userService.addUserWithMember(registration);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "User added successfully.");

        return "redirect:/login";
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

        if (user.getMember() != null) {
            MemberEditDTO memberEditDTO = new MemberEditDTO();
            memberEditDTO.setPhoneNumber(user.getMember().getPhoneNumber());
            memberEditDTO.setAddress(user.getMember().getAddress());
            memberEditDTO.setExpirationDate(user.getMember().getExpirationDate());
            memberEditDTO.setMaxLoans(user.getMember().getMaxLoans());
            memberEditDTO.setStatus(user.getMember().getStatus());
            userDTO.setMember(memberEditDTO);
        }

        model.addAttribute("user", userDTO);
        model.addAttribute("userId", id);

        return "user-form";
    }


    @PreAuthorize("hasRole('ADMINISTRATOR') or #id == authentication.principal.id")
    @PostMapping("/users/edit/{id}")
    public String editUser(@ModelAttribute("user") UserDTO userDTO, RedirectAttributes redirectAttributes, @PathVariable Long id) {

        User user = userService.editUser(id, userDTO);

        if (user.getMember() != null && userDTO.getMember() != null) {
            memberService.editMember(user.getMember().getId(), userDTO.getMember());
        }

        redirectAttributes.addFlashAttribute("successMessage", "User successfully updated");

        return "redirect:/users/" + id;
    }


    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/users/new")
    public String addUserForm(Model model) {

        model.addAttribute("user", new UserDTO());

        return "user-form";
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/users/new")
    public String addUser(@ModelAttribute("user") UserDTO userDTO, RedirectAttributes redirectAttributes) {

        userService.addUser(userDTO);

        redirectAttributes.addFlashAttribute("successMessage", "User added successfully.");

        return "redirect:/users";
    }
}
