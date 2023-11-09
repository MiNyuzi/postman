package com.postman1.postman1.web.controller;

import com.postman1.postman1.service.MemberService;
import com.postman1.postman1.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor //생성자 주입
public class MemberController {
    private final MemberService memberService;

    //회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm(){
        return "save";
    }
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDto memberDto){
        System.out.println("memberController.save");
        System.out.println("memberEmail = " + memberDto);
        memberService.save(memberDto);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession session){
        MemberDto loginResult = memberService.login(memberDto);
        if (loginResult !=null){
            //login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        }else {
            //login 실패
            return "login";
        }
    }
    @GetMapping("/member/")
    public String findAll(Model model){
        List<MemberDto> memberDtoList = memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model사용
        model.addAttribute("memberList", memberDtoList);
        return "list";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model){
        MemberDto memberDto = memberService.findById(id); //한명이라서 dto로 받음 위에 list는 조회하는 데이터가 여러개
        model.addAttribute("member", memberDto);
        return "detail";

    }
    @GetMapping("/member/update")
    public String updateForm(HttpSession Session, Model model){
        String myEmail = (String) Session.getAttribute("loginEmail");
        MemberDto memberDto = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDto);
        return "update";
    }
    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDto memberDto) {
        memberService.update(memberDto);
        return "redirect:/member/" + memberDto.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return "redirect:/member/";   //list만 보내면 겁데기만 남기때문에 redirect를 적어준다.
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
}
