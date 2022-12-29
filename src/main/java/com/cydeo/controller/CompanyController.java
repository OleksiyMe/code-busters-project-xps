package com.cydeo.controller;

import com.cydeo.dto.CompanyDto;
import com.cydeo.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public String listCompanies(Model model){
        model.addAttribute("companies", companyService.listAllCompanies());
        return "/company/company-list";
    }

    @GetMapping("/update/{companyId}")
    public String editCompany(@PathVariable("companyId") Long companyId, Model model){

        model.addAttribute("company",companyService.findCompanyById(companyId));

        return "company/company-update";
    }



    @PostMapping("/update/{companyId}")
    public String updateCompany(@PathVariable("companyId") Long companyId, @Valid @ModelAttribute("company") CompanyDto companyDto, BindingResult bindingResult){

        companyService.updateCompany(companyDto);


        return "redirect:/company/company-list";

    }



}
