package com.cydeo.controller;


import com.cydeo.dto.ClientVendorDto;
import com.cydeo.service.ClientVendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/clientVendors")
public class ClientVendorController {
    private final ClientVendorService clientVendorService;

    public ClientVendorController(ClientVendorService clientVendorService) {
        this.clientVendorService = clientVendorService;
    }

    @GetMapping("/list")
    public String ListClientVendors(Model model) {
        model.addAttribute("clientVendors",
                clientVendorService.listAllClientVendors());

        return "/clientVendor/clientVendor-list";
    }

    @GetMapping("/create")
    public String createClientVendor(Model model) {
        model.addAttribute("newClientVendor", new ClientVendorDto());
        model.addAttribute("clientVendorTypes", clientVendorService.listAllClientVendorTypes());
        return "/clientVendor/clientVendor-create";
    }

    @PostMapping("/create")
    public String createClientVendorFinish(@Valid
                                     @ModelAttribute("newClientVendor") ClientVendorDto clientVendorDto,
                                     BindingResult bindingResult,
                                     Model model) {

        if (bindingResult.hasErrors()){
            model.addAttribute("clientVendorTypes", clientVendorService.listAllClientVendorTypes());
            return "/clientVendor/clientVendor-create";
        }

        clientVendorService.save(clientVendorDto);
        return "redirect:/clientVendors/list";
    }


    @GetMapping("/update/{id}")
    public String editClientVendor(@PathVariable("id") Long id, Model model) {

        model.addAttribute("clientVendor", clientVendorService.findClientVendorById(id));
        model.addAttribute("address", clientVendorService.findClientVendorAddress(id));
        model.addAttribute("clientVendorTypes", clientVendorService.listAllClientVendorTypes());

        return "clientVendor/clientVendor-update";
    }

    @PostMapping("/update/{id}")
    public String updateClientVendor(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("clientVendor") ClientVendorDto clientVendorDto, BindingResult bindingResult) {

        clientVendorDto.setId(id);
        clientVendorDto.setClientVendorType(clientVendorService.findClientVendorById(id).getClientVendorType());

        if (bindingResult.hasErrors()) {
            model.addAttribute("clientVendor", clientVendorDto);
            model.addAttribute("address", clientVendorService.findClientVendorAddress(id));
            model.addAttribute("clientVendorTypes", clientVendorService.listAllClientVendorTypes());
            return "clientVendor/clientVendor-update";
        }

        clientVendorService.updateClientVendor(clientVendorDto);

        return "redirect:/clientVendors/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteClientVendor(@PathVariable("id") Long id) {

        clientVendorService.deleteClientVendor(id);
        return "redirect:/clientVendors/list";
    }
}
