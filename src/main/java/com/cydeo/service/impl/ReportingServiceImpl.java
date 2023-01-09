package com.cydeo.service.impl;

import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.enums.InvoiceStatus;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.InvoiceProductRepository;
import com.cydeo.service.ReportingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportingServiceImpl implements ReportingService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final MapperUtil mapperUtil;

    public ReportingServiceImpl(InvoiceProductRepository invoiceProductRepository, MapperUtil mapperUtil) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<InvoiceProductDto> getStockData() throws Exception {


        return invoiceProductRepository.findAll().stream().filter(invoiceProduct ->
                invoiceProduct.getInvoice().getInvoiceStatus().equals(InvoiceStatus.APPROVED))
                .map(invoiceProduct -> mapperUtil.convert(invoiceProduct,new InvoiceProductDto()))
                .sorted(Comparator.comparing(invoiceProductDto -> invoiceProductDto.getInvoice().getDate()))
                .collect(Collectors.toList());

    }

    @Override
    public Map<String, BigDecimal> getMonthlyProfitLossDataMap() {
        return null;
    }
}
