package com.cydeo.service;

import com.cydeo.dto.InvoiceDto;

public interface InvoiceService {
    InvoiceDto findInvoiceById(Long id);
}
