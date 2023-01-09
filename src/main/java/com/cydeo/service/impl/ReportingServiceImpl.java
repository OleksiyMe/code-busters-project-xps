package com.cydeo.service.impl;

import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.enums.InvoiceStatus;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.InvoiceProductRepository;
import com.cydeo.service.InvoiceProductService;
import com.cydeo.service.ReportingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class ReportingServiceImpl implements ReportingService {

    private final InvoiceProductService invoiceProductService;
    private final MapperUtil mapperUtil;

    public ReportingServiceImpl(InvoiceProductService invoiceProductService, MapperUtil mapperUtil) {
        this.invoiceProductService = invoiceProductService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<InvoiceProductDto> getStockData() throws Exception {

        return invoiceProductService.findAllNotDeletedForCurrentCompanySortByDate().stream()
                .filter(invoiceProductDto ->
                        invoiceProductDto.getInvoice().getInvoiceStatus().equals(InvoiceStatus.APPROVED))
                //  .sorted(Comparator.comparing(invoiceProductDto -> invoiceProductDto.getInvoice().getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<LocalDate, BigDecimal> getMonthlyProfitLossDataMap() {

        Map<LocalDate, BigDecimal> map = new TreeMap<>();
        List<InvoiceProductDto> listOfInvoiceProducts =
                invoiceProductService.findAllNotDeletedForCurrentCompanySortByDate().stream()
                        .filter(ip -> ip.getInvoice().getInvoiceStatus().equals(InvoiceStatus.APPROVED))
                        .collect(Collectors.toList());
        for (InvoiceProductDto eachInvoiceProduct : listOfInvoiceProducts) {
            LocalDate k = reduceToFirstDayOfMonth(eachInvoiceProduct.getInvoice().getDate());
            BigDecimal v = map.getOrDefault(k, BigDecimal.valueOf(0))
                    .add(eachInvoiceProduct.getProfitLoss());
            map.put(k, v);
        }
        return map;
    }

    private LocalDate reduceToFirstDayOfMonth(LocalDate input) {
        return LocalDate.of(input.getYear(), input.getMonth(), 1);
    }
}
