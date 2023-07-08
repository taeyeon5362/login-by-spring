package jpa.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createform() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public  String create(MemberForm form) {
        form.printAll();

        Member member = new Member();

        member.setMid(form.getMid());
        member.setPss(form.getPsw());
        member.setEmail(form.getEmail());
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";

    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/members/login")
    public String enterInfo() {
        return "members/login";
    }

    @PostMapping("/members/login")
    public String logIn(HttpSession session, LoginForm form) {
        if (memberService.LogIn(form.getUserID(), form.getUserPW())) {
            if (memberService.isAdmin(form.getUserID()))
                session.setAttribute("admin", true);
            else session.setAttribute("admin", false);
            session.setAttribute("loginCheck", true);
            session.setAttribute("userID", form.getUserID());
            session.setAttribute("name", form.getUserName());
            session.setAttribute("email", form.getUserEmail());

            return "redirect:/";
        } else return "members/login";

        /*
        @GetMapping("/members/logout")
        public String logOut(HttpSession session) {
            session.setAttribute("loginCheck", false);
            session.setAttribute("userID", null);

            return "redirect:/";
        }

         */
    }
}
