package com.cydeo.service.impl;

import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.service.ReportingService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ReportingServiceImpl implements ReportingService {

    @Override
    public List<InvoiceProductDto> getStockData() throws Exception {
        return null;
    }

    @Override
    public Map<String, BigDecimal> getMonthlyProfitLossDataMap() {
        return null;
    }
}
