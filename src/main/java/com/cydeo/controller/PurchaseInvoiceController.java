package com.cydeo.controller;

import com.cydeo.dto.InvoiceDto;
import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.entity.InvoiceProduct;
import com.cydeo.enums.InvoiceType;
import com.cydeo.service.ClientVendorService;
import com.cydeo.service.InvoiceProductService;
import com.cydeo.service.InvoiceService;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/purchaseInvoices")
public class PurchaseInvoiceController {

    private final InvoiceService invoiceService;
    private final ClientVendorService clientVendorService;
    private final ProductService productService;

    public PurchaseInvoiceController(InvoiceService invoiceService, ClientVendorService clientVendorService, ProductService productService, InvoiceProductService invoiceProductService) {
        this.invoiceService = invoiceService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
    }

    @GetMapping("/list")
    public String purchaseInvoiceList(Model model) {
        model.addAttribute("invoices", invoiceService.listAllPurchaseInvoices());

        return "/invoice/purchase-invoice-list";
    }

    @GetMapping("/create")
    public String createPurchaseInvoice(Model model) {

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setInvoiceNo(invoiceService.generateInvoiceNumber(InvoiceType.PURCHASE));
        invoiceDto.setDate(LocalDate.now());
        model.addAttribute("newPurchaseInvoice", invoiceDto);
        model.addAttribute("vendors", clientVendorService.listAllVendors());

        return "/invoice/purchase-invoice-create";
    }

    @PostMapping("/create")
    public String savePurchaseInvoice(@Valid @ModelAttribute("newPurchaseInvoice") InvoiceDto invoiceDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("vendors", clientVendorService.listAllVendors());
            return "invoice/purchase-invoice-create";
        }

        InvoiceProductDto invoiceProductDto = new InvoiceProductDto();
        invoiceProductDto.setInvoice(invoiceDto);
        model.addAttribute("newInvoiceProduct", invoiceProductDto);
        model.addAttribute("invoice", invoiceDto);
        model.addAttribute("vendors", clientVendorService.listAllVendors());
        model.addAttribute("products", productService.listAllNotDeletedProductsForCurrentCompany());

        invoiceDto.setInvoiceType(InvoiceType.PURCHASE);
        invoiceService.save(invoiceDto);
        return "/invoice/purchase-invoice-update";
    }


    @GetMapping("/approve/{id}")
    public String approvePurchaseInvoice(@PathVariable("id") Long id, Model model) {
        invoiceService.approve(id);
        return "redirect:/purchaseInvoices/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePurchaseInvoice(@PathVariable("id") Long id) {
        invoiceService.deleteInvoice(id);
        return "redirect:/purchaseInvoices/list";
   }

    @GetMapping("/update/{id}")
    public String createPurchaseInvoice(@PathVariable("id") Long id, Model model) {

        InvoiceDto invoiceDto = invoiceService.findInvoiceById(id);
        model.addAttribute("invoice", invoiceDto);
        model.addAttribute("vendors", clientVendorService.listAllVendors());

        return "/invoice/purchase-invoice-update";
    }
}
