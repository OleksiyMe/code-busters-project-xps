package com.cydeo.service.impl;

import com.cydeo.dto.InvoiceDto;
import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.Company;
import com.cydeo.entity.Invoice;
import com.cydeo.enums.InvoiceType;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.InvoiceRepository;
import com.cydeo.service.InvoiceProductService;
import com.cydeo.service.InvoiceService;
import com.cydeo.service.ProductService;
import com.cydeo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceProductService invoiceProductService;
    private final SecurityService securityService;
    private final MapperUtil mapperUtil;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceProductService invoiceProductService, SecurityService securityService, MapperUtil mapperUtil) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceProductService = invoiceProductService;
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
                .map(invoice -> {
                    InvoiceDto invoiceDto = mapperUtil.convert(invoice, new InvoiceDto());
                    List<InvoiceProductDto> list =
                            invoiceProductService.getInvoiceProductsByInvoiceId(invoice.getId());
                    BigDecimal price = new BigDecimal(0);
                    BigDecimal tax= new BigDecimal(0);
                    BigDecimal total= new BigDecimal(0);
                    for (InvoiceProductDto eachProduct : list) {
                        BigDecimal eachTotalBeforeTax =
                                eachProduct.getPrice().multiply(BigDecimal.valueOf(eachProduct.getQuantity()));
                        BigDecimal eachTaxAmount =eachProduct.getTotal().subtract(eachTotalBeforeTax);
                        price=price.add(eachTotalBeforeTax);
                        tax=tax.add(eachTaxAmount);
                        total=total.add(eachProduct.getTotal());
                    }
                    invoiceDto.setPrice(price);
                    invoiceDto.setTax(tax);
                    invoiceDto.setTotal(total);
                    return invoiceDto;
                })
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

        for (int i = 0; i < max.length(); i++) {
            if (Character.isDigit(max.charAt(i))) num += max.charAt(i);
        }

        return "P-" + String.format("%03d", Integer.parseInt(num) + 1);
    }

    @Override
    public String generateDate() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM dd, y");
        return LocalDate.now().format(df);
    }

    @Override
    public List<InvoiceDto> listAllPurchaseInvoices() {

        List<InvoiceDto> list= listAllInvoices().stream()
                .filter(invoiceDto -> invoiceDto.getInvoiceType().equals(InvoiceType.PURCHASE))
                .collect(Collectors.toList());
        return list;
    }


}
