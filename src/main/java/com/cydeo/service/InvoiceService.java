package com.cydeo.service;

import com.cydeo.dto.InvoiceDto;

import java.util.List;

public interface InvoiceService {
    InvoiceDto findInvoiceById(Long id);

    void createInvoice(InvoiceDto invoice);
    List<InvoiceDto> listAllNotDeletedInvoicesForLoggedInUser();


    InvoiceDto updateInvoice(Long id);

    void deleteInvoice(Long id);

    InvoiceDto createPurchaseInvoice(InvoiceDto invoiceDto);

    String generatePurchaseInvoiceNumber();

    List<InvoiceDto> listAllPurchaseInvoices();

    void approve(Long id);

    List<InvoiceDto> listAllSalesInvoices();

}
