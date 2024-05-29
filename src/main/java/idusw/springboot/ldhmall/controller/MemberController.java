package idusw.springboot.ldhmall.controller;

import idusw.springboot.ldhmall.model.Member;
import idusw.springboot.ldhmall.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    // tightly-coupled, 기존제어 : 개발자가 객체를 제어함
    //MemberService memberService = new MemberServiceImpl2();
    final MemberService memberService;

    // loosely-coupled : IoC (Inversion of Control) 제어의 역전, 프레임워큭 객체를 제어함
    // IoC : DL(dependancy lookup), DI(dependency injection) ...
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members/{idx}")
    public String getOne(@PathVariable("idx") Long idx, Model model) {
        Member member = memberService.read(Member.builder().idx(idx).build());
        model.addAttribute("dto", member);
        return "./members/profile";
    }

    @GetMapping("members/login")
    public String getLogin(Model model) {
        //memberService.read(new Member());
        model.addAttribute("member", Member.builder().build());
        return "./members/login";
    }

    @PostMapping("members/login")
    public String postLogin(@ModelAttribute Member member, Model model, HttpSession session) {
        String message = "로그인 실패하셨습니다.";
        Member ret = null; // DBMS에 저장된 정보를 접근하여 생성된 객체
        if((ret = memberService.login(member)) != null){
            session.setAttribute("id", ret.getEmail());
            session.setAttribute("idx", ret.getIdx());
            message = ret.getId() + "님" + "로그인 성공하셨습니다.";
        }
        model.addAttribute("msg", message);
        return "./members/welcome";
    }

    @GetMapping("members/logout")
    public String getLogout(HttpSession session) {
        session.invalidate();
        // return "./main/index"; //main/index.html : surffix, prefix 설정을 안한 기본상태
        return "redirect:/";
    }

    @GetMapping("members/register")
    public String getRegister(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("msg", "등록에 성공/실패하셨습니다.");
        return "./members/register";
    }

    @PostMapping("members/register")
    public String postRegister(@ModelAttribute Member member, Model model) {
        model.addAttribute("msg", "등록에 성공/실패하셨습니다.");
        return "./members/reg";
    }

    @GetMapping(value = {"members/", "members"})
    public String getMembers(HttpSession session, Model model) { //회원 목록 조회 요청
        // admin 이면 정상 동장 아니면 오류 발생
        if(session.getAttribute("id").equals("admin@induk.ac.kr")) {
            List<Member> dtoList = memberService.readList();
            model.addAttribute("dtoList", dtoList);
            return "./members/list"; // members/list.html을 view로 지정하고,model을 전달함
        }
        else
            return "./error/400";
    }
}