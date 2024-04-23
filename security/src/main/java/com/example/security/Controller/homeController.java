package com.example.security.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {

    @GetMapping("/color")
    public String color(org.springframework.ui.Model model)
    {   model.addAttribute("title","Home Page");
        return"utilities-color";
    }
}
