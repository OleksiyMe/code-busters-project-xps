package com.cydeo.controller;

import com.cydeo.dto.InvoiceDto;
import com.cydeo.dto.ProductDto;
import com.cydeo.service.ClientVendorService;
import com.cydeo.service.InvoiceService;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/purchaseInvoices")
public class PurchaseInvoiceController {

    private final InvoiceService invoiceService;
    private final ClientVendorService clientVendorService;

    public PurchaseInvoiceController(InvoiceService invoiceService, ClientVendorService clientVendorService) {
        this.invoiceService = invoiceService;
        this.clientVendorService = clientVendorService;
    }

    @GetMapping("/list")
    public String purchaseInvoiceList(Model model) {
        model.addAttribute("invoices", invoiceService.listAllPurchaseInvoices());

        return "/invoice/purchase-invoice-list";
    }

    @GetMapping("/create")
    public String createPurchaseInvoice(Model model) {
        model.addAttribute("newPurchaseInvoice", new InvoiceDto());
        model.addAttribute("vendors", clientVendorService.listAllVendors());
        model.addAttribute("date", invoiceService.generateDate());
        model.addAttribute("invoiceNo", invoiceService.generatePurchaseInvoiceNumber());
        model.addAttribute("product", new ProductDto());
        return "/invoice/purchase-invoice-create";
    }

    @PostMapping("create")
    public String savePurchaseInvoice(@ModelAttribute("invoice") InvoiceDto invoiceDto) {
        return "redirect:/purchaseInvoices/create";
    }

    @GetMapping("/approve/{id}")
    public String approvePurchaseInvoice(@PathVariable("id") Long id, Model model) {
        invoiceService.approve(id);

        return "redirect:/purchaseInvoices/list";
    }


}
