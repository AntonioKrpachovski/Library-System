package library.management.librarymanagement.web;

import library.management.librarymanagement.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

}
