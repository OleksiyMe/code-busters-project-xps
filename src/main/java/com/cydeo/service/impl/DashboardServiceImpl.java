package com.cydeo.service.impl;

import com.cydeo.dto.CurrencyDto;
import com.cydeo.dto.InvoiceDto;
import com.cydeo.service.DashboardService;
import com.cydeo.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final InvoiceService invoiceService;

    public DashboardServiceImpl(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Override
    public Map<String, BigDecimal> getSummaryNumbers() throws Exception {
         Map<String, BigDecimal> financialActivities = new HashMap<>();

         financialActivities.put("totalCost", calculateTotalCost());
         financialActivities.put("totalSales", calculateTotalSales());
         financialActivities.put("profitLoss", BigDecimal.valueOf(5000));

        return financialActivities;
    }

    private BigDecimal calculateTotalCost(){
        BigDecimal num = invoiceService.listAllNotDeletedInvoicesForLoggedInUser().stream()
                .filter(invoiceDto -> invoiceDto.getInvoiceStatus().getValue().equals("Approved"))
                .filter(invoiceDto -> invoiceDto.getInvoiceType().getValue().equals("Purchase"))
                .map(InvoiceDto::getTotal)
                .reduce(BigDecimal::add).get();
        return num;
    }

    private BigDecimal calculateTotalSales(){
        BigDecimal num = invoiceService.listAllNotDeletedInvoicesForLoggedInUser().stream()
                .filter(invoiceDto -> invoiceDto.getInvoiceStatus().getValue().equals("Approved"))
                .filter(invoiceDto -> invoiceDto.getInvoiceType().getValue().equals("Sales"))
                .map(InvoiceDto::getTotal)
                .reduce(BigDecimal::add).get();
        return num;
    }

    @Override
    public CurrencyDto getExchangeRates() {
        return null;
    }
}
