package com.jova.jova.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {

    @GetMapping("/a-propos")
    public String showAboutPage() {
        return "a-propos";
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }
}

