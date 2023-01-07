package com.cydeo.service;

import com.cydeo.dto.CurrencyDto;
import com.cydeo.dto.InvoiceDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DashboardService {

    Map<String, BigDecimal> getSummaryNumbers() throws Exception;
    CurrencyDto getExchangeRates();

    List<InvoiceDto> getLast3TransactionsByDate();


}
