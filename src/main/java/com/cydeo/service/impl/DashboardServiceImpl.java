package com.cydeo.service.impl;

import com.cydeo.dto.InvoiceDto;
import com.cydeo.entity.Invoice;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.InvoiceRepository;
import com.cydeo.service.DashboardService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {
    private final InvoiceRepository invoiceRepository;
    private final MapperUtil mapperUtil;

    public DashboardServiceImpl(InvoiceRepository invoiceRepository, MapperUtil mapperUtil) {
        this.invoiceRepository = invoiceRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<InvoiceDto> getLast3TransactionsByDate() {
        List<Invoice> AlltheInvoices=invoiceRepository.findAll();
        return AlltheInvoices.stream().sorted(Comparator.comparing(Invoice::getDate).reversed()).limit(3)
                .map(invoice -> mapperUtil.convert(invoice,new InvoiceDto())).collect(Collectors.toList());
    }
}
