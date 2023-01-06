package com.cydeo.controller;

import com.cydeo.dto.InvoiceDto;
import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.enums.InvoiceType;
import com.cydeo.service.ClientVendorService;
import com.cydeo.service.InvoiceService;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/salesInvoices")
public class SalesInvoiceController {

    private final InvoiceService invoiceService;
    private final ClientVendorService clientVendorService;
    private final ProductService productService;

    public SalesInvoiceController(InvoiceService invoiceService, ClientVendorService clientVendorService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
    }

    @GetMapping("/create")
    public String createSalesInvoice(Model model){

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setInvoiceNo(invoiceService.generateInvoiceNumber(InvoiceType.SALES));
        invoiceDto.setDate(LocalDate.now());
        model.addAttribute("newSalesInvoice", invoiceDto);
        model.addAttribute("clients", clientVendorService.listAllClients());

        return "/invoice/sales-invoice-create";
    }

    @PostMapping("/create")
    public String saveSalesInvoice(@Valid @ModelAttribute("newSalesInvoice") InvoiceDto invoiceDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("clients", clientVendorService.listAllClients());
            return "invoice/sales-invoice-create";
        }

        InvoiceProductDto invoiceProductDto = new InvoiceProductDto();
        model.addAttribute("newInvoiceProduct", invoiceProductDto);
        model.addAttribute("invoice", invoiceDto);
        model.addAttribute("clients", clientVendorService.listAllClients());
        model.addAttribute("products", productService.listAllProducts());

        invoiceDto.setInvoiceType(InvoiceType.SALES);
        invoiceService.save(invoiceDto);
        return "/invoice/sales-invoice-update";
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

    @GetMapping("/list")
    public String salesInvoiceList(Model model) {
        model.addAttribute("invoices", invoiceService.listAllSalesInvoices());

        return "/invoice/sales-invoice-list";
    }


}

