package com.example.tunisartisan.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Bienvenue !");
        return "index"; // correspond à index.html
    }
    @GetMapping("/testlogin")
    public String testLogin(Model model) {
        model.addAttribute("name", "Bienvenue !");
        return "testlogin"; // correspond à testlogin.html
    }

}