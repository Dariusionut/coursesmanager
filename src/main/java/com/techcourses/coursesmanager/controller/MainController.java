package com.techcourses.coursesmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/")
public class MainController {

//    @GetMapping("/welcome")
//    public String welcome(@RequestParam(value = "name", required = false, defaultValue = "user")
//                                  String name, Model model) {
//        model.addAttribute("welcome", new String("Welcome to Tech Coding, " + name + "!"));
//        return "/welcome";
//    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("welcome", "Welcome to Tech Coding");
        return "/welcome";
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }
}
