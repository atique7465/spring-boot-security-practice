package com.atique.springbootsecuritypractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class TemplateController {
    @GetMapping(value = "login")
    public String getLogin() {
        return "login";
    }

    @GetMapping(value = "courses")
    public String getCourses() {
        return "courses";
    }
}
