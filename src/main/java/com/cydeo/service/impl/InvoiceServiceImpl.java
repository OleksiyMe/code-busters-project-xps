package com.cydeo.service.impl;

import com.cydeo.dto.InvoiceDto;
import com.cydeo.entity.Company;
import com.cydeo.entity.Invoice;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.InvoiceRepository;
import com.cydeo.service.InvoiceService;
import com.cydeo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final MapperUtil mapperUtil;
    private final SecurityService securityService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, MapperUtil mapperUtil, SecurityService securityService) {
        this.invoiceRepository = invoiceRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
    }

    @Override
    public InvoiceDto findInvoiceById(Long id) {
        return mapperUtil.convert(invoiceRepository.findById(id).get(), new InvoiceDto());
    }

    @Override
    public void createInvoice(InvoiceDto invoice) {

    }

    @Override
    public List<InvoiceDto> listAllInvoices() {
        return null;
    }

    @Override
    public InvoiceDto updateInvoice(Long id) {
        return null;
    }

    @Override
    public void deleteInvoice(Long id) {

    }

    @Override
    public List<InvoiceDto> listAllPurchaseInvoices() {

        Company currentCompany = mapperUtil.convert(
                securityService.getLoggedInUser().getCompany(), new Company());

        List<Invoice> list = invoiceRepository.findAll();

        return list.stream().filter(invoice -> invoice.getCompany().getId().equals(currentCompany.getId()))
                .filter(invoice -> invoice.getInvoiceType()
                .getValue().equals("Purchase")).sorted(Comparator.comparing(Invoice::getInvoiceNo).reversed())
                .map(invoice -> mapperUtil.convert(invoice,new InvoiceDto())).collect(Collectors.toList());
    }
}
