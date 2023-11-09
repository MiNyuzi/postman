package com.postman1.postman1.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //기본 페이지 요청 메서드
    @GetMapping("/")
    public String email(){
        return "index"; // templates폴더의 index.html을 찾아감
    }
}
