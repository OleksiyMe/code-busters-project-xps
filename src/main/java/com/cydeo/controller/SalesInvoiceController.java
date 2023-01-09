package com.cydeo.controller;

import com.cydeo.dto.ClientVendorDto;
import com.cydeo.dto.InvoiceDto;
import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.enums.ClientVendorType;
import com.cydeo.enums.InvoiceType;
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
import java.util.Arrays;

@Controller
@RequestMapping("/salesInvoices")
public class SalesInvoiceController {

    private final InvoiceService invoiceService;
    private final ClientVendorService clientVendorService;
    private final ProductService productService;
    private final InvoiceProductService invoiceProductService;

    public SalesInvoiceController(InvoiceService invoiceService, ClientVendorService clientVendorService, ProductService productService, InvoiceProductService invoiceProductService) {
        this.invoiceService = invoiceService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
        this.invoiceProductService = invoiceProductService;
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
        model.addAttribute("products", productService.listAllNotDeletedProductsForCurrentCompany());

        invoiceDto.setInvoiceType(InvoiceType.SALES);
        invoiceService.save(invoiceDto);
        return "/invoice/sales-invoice-update";
    }
//--------------------------------------------------------------------------uosil  bas
@GetMapping("/update/{id}")
public String editSalesInvoice(@PathVariable("id") Long id, Model model){


    model.addAttribute("invoice", invoiceService.findInvoiceById(id));

    model.addAttribute("clients", clientVendorService.findById(invoiceService.findInvoiceById(id).getClientVendor().getId()));

    model.addAttribute("newInvoiceProduct", new InvoiceProductDto());
    model.addAttribute("newInvoiceProducts",invoiceProductService.getInvoiceProductsByInvoiceId(id));

    model.addAttribute("products", productService.listAllNotDeletedProductsForCurrentCompany());

    return "/invoice/sales-invoice-update";
}

//    @PostMapping("/update/{id}")
//    public String update(/*@Valid */@PathVariable("id") Long id,@ModelAttribute("clientVendor") ClientVendorDto clientVendorDto,
//                                    BindingResult bindingResult, Model model,InvoiceDto invoiceDto) {
//
//
//        if(bindingResult.hasErrors()){
//            model.addAttribute("clientVendor", clientVendorDto);
//            model.addAttribute("clientVendorTypes", Arrays.asList(ClientVendorType.values()));
//
//            return "clientVendor/clientVendor-update";
//        }
//        invoiceService.save(invoiceDto);
//
//       return "redirect:/salesInvoices/list";
//    }
//    @PostMapping("/addInvoiceProduct/{id}")
//    public String addInvoiceProductToSalesInvoice(@PathVariable("id") Long id,
//                                             @ModelAttribute("invoice") InvoiceDto invoiceDto,Model model) {
//
//        InvoiceProductDto invoiceProductDto = new InvoiceProductDto();
//        model.addAttribute("newInvoiceProduct", invoiceProductService.findAllNotDeletedForCurrentCompany());
//        return "redirect:/salesInvoices/update/";
//    }
//

    ////--------------------------------------------------------------------------uosil son


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

