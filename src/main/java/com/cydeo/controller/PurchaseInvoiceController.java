package com.cydeo.controller;

import com.cydeo.dto.InvoiceDto;
import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.entity.InvoiceProduct;
import com.cydeo.service.ClientVendorService;
import com.cydeo.service.InvoiceProductService;
import com.cydeo.service.InvoiceService;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

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
    public String purchaseInvoiceList(Model model){
        model.addAttribute("invoices", invoiceService.listAllPurchaseInvoices());
        return "/invoice/purchase-invoice-list";
    }

    @GetMapping("/create")
    public String createPurchaseInvoice(Model model){

        InvoiceDto invoiceDto=new InvoiceDto();
        invoiceDto.setInvoiceNo(invoiceService.generatePurchaseInvoiceNumber());
        invoiceDto.setDate(LocalDate.now());
        model.addAttribute("newPurchaseInvoice", invoiceDto);
        model.addAttribute("vendors", clientVendorService.listAllVendors());
     //   model.addAttribute("date", invoiceService.generateDate());
   //     model.addAttribute("invoiceNumber", invoiceService.generatePurchaseInvoiceNumber());

        return "/invoice/purchase-invoice-create";
    }

    @PostMapping("/create")
    public String savePurchaseInvoice(@Valid @ModelAttribute("newPurchaseInvoice") InvoiceDto invoiceDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("newPurchaseInvoice", new InvoiceDto());
            model.addAttribute("vendors", clientVendorService.listAllVendors());
            model.addAttribute("date", invoiceService.generateDate());
            model.addAttribute("invoiceNumber", invoiceService.generatePurchaseInvoiceNumber());
            return "/invoice/purchase-invoice-create";
        }


        InvoiceProductDto invoiceProductDto=new InvoiceProductDto();
        invoiceProductDto.setInvoice(invoiceDto);
        model.addAttribute("newInvoiceProduct", invoiceProductDto);
        model.addAttribute("invoice", invoiceDto);
        model.addAttribute("vendors", clientVendorService.listAllVendors());
//        model.addAttribute("date", invoiceDto.getDate());
//        model.addAttribute("invoiceNo", invoiceDto.getInvoiceNo());
        model.addAttribute("products", productService.listAllProducts());

        invoiceService.createInvoice(invoiceDto);
        return "/invoice/purchase-invoice-update";
    }


}
