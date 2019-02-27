package com.example.calculator.controller;


import com.example.calculator.model.Compressor;
import com.example.calculator.model.User;
import com.example.calculator.service.CompressorCalcService;
import com.example.calculator.service.CompressorService;
import com.example.calculator.service.MailService;
import com.example.calculator.service.ResultsSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;

@Controller
public class FormController {

    @Autowired
    ResultsSevice resultsSevice;
    @Autowired
    CompressorService compressorService;
    @Autowired
    CompressorCalcService compressorCalcService;

    @GetMapping("/form")
    public String formGet(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/form")
    public String formPost(@Valid User user, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            model.addAttribute("noErrors", true);
        }

        model.addAttribute("user", user);
        model.addAttribute("info", "Email was sent. Thank you!");
        System.out.println(user.getName());

        long resultToSend = compressorCalcService.sumOfPotencial(compressorService);
        resultsSevice.sendResults(resultToSend,user);


        return "form";
        //return "ok.html";
    }
}
