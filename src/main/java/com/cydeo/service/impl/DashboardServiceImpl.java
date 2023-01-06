package com.cydeo.service.impl;

import com.cydeo.dto.CurrencyDto;
import com.cydeo.service.DashboardService;

import java.math.BigDecimal;
import java.util.Map;

public class DashboardServiceImpl implements DashboardService {
    @Override
    public Map<String, BigDecimal> getSummaryNumbers() throws Exception {
        return null;
    }

    @Override
    public CurrencyDto getExchangeRates() {
        return null;
    }
}
