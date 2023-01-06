package com.cydeo.service.impl;

import com.cydeo.dto.CurrencyDto;
import com.cydeo.service.DashboardService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Override
    public Map<String, BigDecimal> getSummaryNumbers() throws Exception {
         Map<String, BigDecimal> financialActivities = new HashMap<>();

         financialActivities.put("totalCost", BigDecimal.valueOf(5000));
         financialActivities.put("totalSales", BigDecimal.valueOf(5000));
         financialActivities.put("profitLoss", BigDecimal.valueOf(5000));

        return financialActivities;
    }

    @Override
    public CurrencyDto getExchangeRates() {
        return null;
    }
}
