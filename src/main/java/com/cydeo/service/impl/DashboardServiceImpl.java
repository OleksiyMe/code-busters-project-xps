package com.cydeo.service.impl;

import com.cydeo.dto.CurrencyDto;
import com.cydeo.dto.InvoiceDto;
import com.cydeo.service.DashboardService;
import com.cydeo.service.InvoiceProductService;
import com.cydeo.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;

    public DashboardServiceImpl(InvoiceService invoiceService, InvoiceProductService invoiceProductService) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
    }

    @Override
    public Map<String, BigDecimal> getSummaryNumbers() throws Exception {
         Map<String, BigDecimal> financialActivities = new HashMap<>();

         financialActivities.put("totalCost", calculateTotalCost());
         financialActivities.put("totalSales", calculateTotalSales());
         financialActivities.put("profitLoss", calculateProfitLoss());

        return financialActivities;
    }

    private BigDecimal calculateTotalCost(){
        return invoiceService.listAllNotDeletedInvoicesForLoggedInUser().stream()
                .filter(invoiceDto -> invoiceDto.getInvoiceStatus().getValue().equals("Approved"))
                .filter(invoiceDto -> invoiceDto.getInvoiceType().getValue().equals("Purchase"))
                .map(InvoiceDto::getTotal)
                .reduce(BigDecimal::add).get();

    }

    private BigDecimal calculateTotalSales(){
        return invoiceService.listAllNotDeletedInvoicesForLoggedInUser().stream()
                .filter(invoiceDto -> invoiceDto.getInvoiceStatus().getValue().equals("Approved"))
                .filter(invoiceDto -> invoiceDto.getInvoiceType().getValue().equals("Sales"))
                .map(InvoiceDto::getTotal)
                .reduce(BigDecimal::add).get();
    }

    private BigDecimal calculateProfitLoss(){
        return  null;
    }

    @Override
    public CurrencyDto getExchangeRates() {
        return null;
    }
}
