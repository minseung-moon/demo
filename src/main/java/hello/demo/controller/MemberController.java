package hello.demo.controller;

import hello.demo.domain.Member;
import hello.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
// Spring이 처음에 뜰 때 spring container가 생기는데 거기에 controller에노테이션의 객체를 생성해서 spring에 넣어둔다
// 그리고 spring container에서 관리를 한다, (spring container에서 spring bean이 관리된다)
public class MemberController {
    private final MemberService memberService;

    @Autowired
    // 멤버 변수를 스프링이 스프링 컨테이너에 있는 멤버서비스를 가져다가 연결을 시켜준다
    // DI, Defendency Injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    // form의 method의 post형식으로 전달 받음
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "/members/memberList";
    }
}
