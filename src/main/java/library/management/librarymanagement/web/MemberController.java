package library.management.librarymanagement.web;

import library.management.librarymanagement.model.Member;
import library.management.librarymanagement.model.User;
import library.management.librarymanagement.service.MemberService;
import library.management.librarymanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    private final UserService userService;

    @GetMapping("/members")
    public String membersView(Model model,
                              @RequestParam(required = false) String searchMembershipNumber,
                              @RequestParam(required = false) String searchFirstName,
                              @RequestParam(required = false) String searchLastName,
                              @RequestParam(required = false) String searchEmail,
                              @RequestParam(required = false) String searchPhoneNumber){

        model.addAttribute("users", userService.searchMembers(searchMembershipNumber, searchFirstName, searchLastName, searchEmail, searchPhoneNumber));
        return "users";
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/members/deactivate/{id}")
    public String deactivateMember(@PathVariable Long id){

        User user = userService.findUserById(id);
        Member member = user.getMember();
        memberService.deactivateMember(member.getId());
        return "redirect:/users/" + user.getId();
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/members/suspend/{id}")
    public String suspendMember(@PathVariable Long id){

        User user = userService.findUserById(id);
        Member member = user.getMember();
        memberService.suspendMember(member.getId());
        return "redirect:/users/" + user.getId();
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/members/reactivate/{id}")
    public String reactivateMember(@PathVariable Long id){

        User user = userService.findUserById(id);
        Member member = user.getMember();
        memberService.reactivateMember(member.getId());
        return "redirect:/users/" + user.getId();
    }
}
