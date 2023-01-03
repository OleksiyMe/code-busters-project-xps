package com.cydeo.service.impl;

import com.cydeo.dto.InvoiceDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.Company;
import com.cydeo.entity.Invoice;
import com.cydeo.enums.InvoiceType;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.InvoiceRepository;
import com.cydeo.service.InvoiceService;
import com.cydeo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final SecurityService securityService;
    private final MapperUtil mapperUtil;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, SecurityService securityService, MapperUtil mapperUtil) {
        this.invoiceRepository = invoiceRepository;
        this.securityService = securityService;
        this.mapperUtil = mapperUtil;
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
        UserDto loggedInUser = securityService.getLoggedInUser();
        return invoiceRepository.findAllNotDeleted().stream()
                .filter(invoice -> invoice.getCompany().getId().equals(loggedInUser.getCompany().getId()))
                .map(invoice -> mapperUtil.convert(invoice, new InvoiceDto()))
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDto updateInvoice(Long id) {
        return null;
    }

    @Override
    public void deleteInvoice(Long id) {

    }
    @Override
    public InvoiceDto createPurchaseInvoice(InvoiceDto invoiceDto) {
        invoiceDto.setInvoiceNo(generatePurchaseInvoiceNumber());

        invoiceRepository.save(mapperUtil.convert(invoiceDto, new Invoice()));
        return invoiceDto;
    }

    @Override
    public String generatePurchaseInvoiceNumber() {
        Company currentCompany = mapperUtil.convert(
                securityService.getLoggedInUser().getCompany(), new Company());

        String max = invoiceRepository.findMaxId(currentCompany.getId()).toString();

        String num = "";

        for (int i = 0; i <max.length() ; i++) {
            if(Character.isDigit(max.charAt(i))) num += max.charAt(i);
        }

        return "P-" + String.format("%03d",Integer.parseInt(num) + 1 );
    }

    @Override
    public String generateDate() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM dd, y");
        return LocalDate.now().format(df);
    }

    @Override
    public List<InvoiceDto> listAllPurchaseInvoices() {
        UserDto loggedInUser = securityService.getLoggedInUser();
        return invoiceRepository.findAllNotDeleted().stream()
                .filter(invoice -> invoice.getCompany().getId().equals(loggedInUser.getCompany().getId()))
                .filter(invoice -> invoice.getInvoiceType().equals(InvoiceType.PURCHASE))
                .map(invoice -> mapperUtil.convert(invoice, new InvoiceDto()))
                .collect(Collectors.toList());
    }


}
