package com.cydeo.service.impl;

import com.cydeo.dto.InvoiceDto;
import com.cydeo.service.DashboardService;
import com.cydeo.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final InvoiceService invoiceService;

    public DashboardServiceImpl(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Override
    public List<InvoiceDto> getLast3TransactionsByDate() {
        return invoiceService.lastThreeTransactions();
    }
}
