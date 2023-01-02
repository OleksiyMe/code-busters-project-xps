package com.cydeo.service;

import com.cydeo.dto.CompanyDto;
import com.cydeo.dto.InvoiceDto;

import java.util.List;

public interface InvoiceService {
    InvoiceDto findInvoiceById(Long id);

    void createInvoice(InvoiceDto invoice);
    List<InvoiceDto> listAllInvoices();
    InvoiceDto updateInvoice(Long id);
    void deleteInvoice(Long id);

    List<InvoiceDto> listAllPurchaseInvoices();






}
