package com.cydeo.service.impl;

import com.cydeo.client.ConsumeCurrencyClient;
import com.cydeo.dto.CurrencyDto;
import com.cydeo.dto.InvoiceDto;
import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.dto.feign.ConsumedCurrencyDto;
import com.cydeo.service.DashboardService;
import com.cydeo.service.InvoiceProductService;
import com.cydeo.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;

    private final ConsumeCurrencyClient consumeCurrencyClient;

    public DashboardServiceImpl(InvoiceService invoiceService, InvoiceProductService invoiceProductService, ConsumeCurrencyClient consumeCurrencyClient) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.consumeCurrencyClient = consumeCurrencyClient;
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
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private BigDecimal calculateTotalSales(){
        return invoiceService.listAllNotDeletedInvoicesForLoggedInUser().stream()
                .filter(invoiceDto -> invoiceDto.getInvoiceStatus().getValue().equals("Approved"))
                .filter(invoiceDto -> invoiceDto.getInvoiceType().getValue().equals("Sales"))
                .map(InvoiceDto::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateProfitLoss(){

        BigDecimal profitLossSum = BigDecimal.ZERO;
        for (InvoiceProductDto invoiceProductDto : invoiceProductService.findAllNotDeletedForCurrentCompany()) {
            profitLossSum = profitLossSum.add(invoiceProductDto.getProfitLoss());
        }
        return  profitLossSum;
    }

    @Override
    public CurrencyDto getExchangeRates() {

        ConsumedCurrencyDto consumedCurrencyDto = consumeCurrencyClient.getCurrency();

        CurrencyDto currencyDto=CurrencyDto.builder()
                .euro(consumedCurrencyDto.getRates().getEur())
                .britishPound(consumedCurrencyDto.getRates().getGbp())
                .canadianDollar(consumedCurrencyDto.getRates().getCad())
                .japaneseYen(consumedCurrencyDto.getRates().getJpy())
                .indianRupee(consumedCurrencyDto.getRates().getInr()).build();


        return currencyDto;
    }

    @Override
    public List<InvoiceDto> getLast3TransactionsByDate() {
        return invoiceService.getLastThreeInvoices();
    }
}
