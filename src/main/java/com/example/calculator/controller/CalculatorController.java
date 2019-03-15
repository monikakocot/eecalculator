package com.example.calculator.controller;


import com.example.calculator.model.Compressor;
import com.example.calculator.model.CompressorForm;
import com.example.calculator.service.CompressorCalcService;
import com.example.calculator.service.CompressorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CalculatorController {

    @Autowired
    private CompressorService compressorService;
    @Autowired
    private CompressorCalcService compressorCalcService;

    @GetMapping(value = "/")
    public String showAll(Model model) {
        model.addAttribute("compressors", compressorService.findAll());

        return "allCompressors";
    }

    @GetMapping(value = "/create")
    public String showCreateForm(Model model) {
        
        CompressorForm compressorForm = new CompressorForm();
        compressorForm.addCompressor(new Compressor());
        model.addAttribute("form", compressorForm);

        return "createCompressorForm";
    }

    @GetMapping(value = "/edit")
    public String showEditForm(Model model) {
        List<Compressor> compressors = new ArrayList<>();
        compressorService.findAll()
                .iterator()
                .forEachRemaining(compressors::add);

        model.addAttribute("form", new CompressorForm(compressors));

        return "editCompressorForm";
    }

    @PostMapping(value = "/save")
    public String saveCompressor(@ModelAttribute CompressorForm form,
                                 @RequestParam("workHours") long workHours,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        compressorService.saveAll(form.getCompressors());
        compressorCalcService.setWorkHours(workHours);


        for (int i=0; i<form.getCompressors().size(); i++) {
            Compressor compressor = form.getCompressors().get(i);

            compressor.setPotencialEnergy(compressorCalcService.calculatePotencial(compressor));
            compressor.setPotencialEnergyGJ(compressorCalcService.calculatePotencialToGJ(compressor));
            compressor.setPotencialPower(compressorCalcService.calculatePotencialPower(compressor));

            System.out.println(compressor.toString());

        }

        redirectAttributes.addFlashAttribute("sumOfPower",compressorCalcService.sumOfPower(compressorService));
        redirectAttributes.addFlashAttribute("sumOfEnergy",compressorCalcService.sumOfEnergy(compressorService));
        redirectAttributes.addFlashAttribute("sumOfPotencial",compressorCalcService.sumOfPotencial(compressorService));
        redirectAttributes.addFlashAttribute("sumOfPotencialGJ",compressorCalcService.sumOfPotencialGJ(compressorService));
        redirectAttributes.addFlashAttribute("sumOfPotencialPower",compressorCalcService.sumOfPotencialPower(compressorService));

        System.out.println(compressorCalcService.sumOfPotencial(compressorService));
        return "redirect:/";

    }
}