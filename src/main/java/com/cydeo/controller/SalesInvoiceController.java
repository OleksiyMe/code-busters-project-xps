package com.cydeo.controller;

import com.cydeo.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/salesInvoices")
public class SalesInvoiceController {

    private final InvoiceService invoiceService;

    public SalesInvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/delete/{id}")
    public String deleteSalesInvoice(@PathVariable("id") Long id) {

        invoiceService.deleteInvoice(id);

        return "redirect:/salesInvoices/list";
    }
    @GetMapping("/approve/{id}")
    public String approveSalesInvoice(@PathVariable Long id) {

        invoiceService.approve(id);

        return "redirect:/salesInvoices/list";
    }


}

