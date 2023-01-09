package com.cydeo.service.impl;

import com.cydeo.service.InvoiceProductService;
import com.cydeo.service.ProfitLossService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
public class ProfitLossServiceImpl implements ProfitLossService {

    private final InvoiceProductService invoiceProductService;

    public ProfitLossServiceImpl(InvoiceProductService invoiceProductService) {
        this.invoiceProductService = invoiceProductService;
    }

    @Override
    public Map<LocalDate, BigDecimal> getProfitLossByMonth() {

//        invoiceProductService.findAllNotDeletedForCurrentCompany().stream()
//                .count()


      return null;
    }
}
