package com.cydeo.controller;

import com.cydeo.dto.CompanyDto;
import com.cydeo.enums.CompanyStatus;
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
    public String listCompanies(Model model) {
        model.addAttribute("companies", companyService.listAllCompanies());
        return "/company/company-list";
    }

    @GetMapping("/create")
    public String createCompany(Model model) {
        model.addAttribute("newCompany", new CompanyDto());
        return "/company/company-create";
    }

    @PostMapping("/create")
    public String createCompanyFinish(@Valid @ModelAttribute("newCompany") CompanyDto companyDto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/company/company-create";
        }
        companyService.save(companyDto);
        return "redirect:/companies/list";
    }

    @GetMapping("/update/{companyId}")
    public String editCompany(@PathVariable("companyId") Long companyId, Model model) {
        model.addAttribute("company", companyService.findCompanyById(companyId));
        return "company/company-update";
    }

    @PostMapping("/update/{companyId}")
    public String updateCompany(@PathVariable("companyId") Long companyId, @Valid @ModelAttribute("company") CompanyDto companyDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/company/company-update";
        }
        companyDto.setId(companyId);
        companyService.updateCompany(companyDto);
        return "redirect:/companies/list";
    }

    @GetMapping("/activate/{id}")
    public String activate(@PathVariable("id") Long id) {
        companyService.activate(id);
        return "redirect:/companies/list";
    }

    @GetMapping("/deactivate/{id}")
    public String deactivate(@PathVariable("id") Long id) {
        companyService.deactivate(id);
        return "redirect:/companies/list";
    }

}
