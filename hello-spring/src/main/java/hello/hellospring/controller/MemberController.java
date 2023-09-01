package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 해당 어노테이션이 있으면 스프링 컨테이너에 멤버 컨트롤러 객체를 생성해 스프링이 관리를 한다.
public class MemberController {

    private MemberService memberService;

    @Autowired // 스프링 컨테이너에서 가져온다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members/new")
    public String createForm() {

        return "members/createMemberHome";
    }

    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",  members);

        return "members/memberList";
    }
}
