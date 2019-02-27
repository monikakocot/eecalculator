package com.example.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JSController {

    @GetMapping(value = "/js")
    public String showAll() {
        return "index";
    }
}
