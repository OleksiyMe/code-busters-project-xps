package com.cydeo.service;

import com.cydeo.dto.InvoiceDto;
import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.enums.InvoiceType;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceService {
    InvoiceDto findInvoiceById(Long id);

    void createInvoice(InvoiceDto invoice);
    List<InvoiceDto> listAllNotDeletedInvoicesForLoggedInUser();


    InvoiceDto updateInvoice(Long id);

    void deleteInvoice( Long id);

    InvoiceDto save(InvoiceDto invoiceDto);

    String generateInvoiceNumber(InvoiceType invoiceType);

    List<InvoiceDto> listAllPurchaseInvoices();

    void approve(Long id);

    List<InvoiceDto> listAllSalesInvoices();
    List<InvoiceDto> getLastThreeInvoices();

    BigDecimal calculateProfitLossForInvoiceProduct(InvoiceProductDto invoiceProductDto);

    String invoiceCanBePrinted(Long invoiceId);

    String SalesInvoiceCanBeApproved(Long invoiceId);
}
