package com.cydeo.service.impl;

import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.entity.*;
import com.cydeo.entity.InvoiceProduct;
import com.cydeo.entity.Product;
import com.cydeo.enums.InvoiceType;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.InvoiceProductRepository;
import com.cydeo.service.InvoiceProductService;
import com.cydeo.service.InvoiceService;
import com.cydeo.service.ProductService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final InvoiceService invoiceService;
    private final ProductService productService;
    private final MapperUtil mapperUtil;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository,
                                     @Lazy InvoiceService invoiceService, ProductService productService,
                                     MapperUtil mapperUtil) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.invoiceService = invoiceService;
        this.productService = productService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public InvoiceProductDto findInvoiceProductById(long id) {
        return mapperUtil.convert(invoiceProductRepository.findInvoiceProductById(id), new InvoiceProductDto());
    }

    @Override
    public List<InvoiceProductDto> getInvoiceProductsByInvoiceId(Long invoiceId) {
        Invoice invoice = mapperUtil.convert(invoiceService.findInvoiceById(invoiceId), new Invoice());
        return invoiceProductRepository
                .findAllByInvoice(invoice)
                .stream()
                .sorted(Comparator.comparing((InvoiceProduct each) -> each.getInvoice().getInvoiceNo()).reversed())
                .map(each -> {
                    InvoiceProductDto dto = mapperUtil.convert(each, new InvoiceProductDto());
                    dto.setTotal(each.getPrice().multiply(BigDecimal.valueOf(each.getQuantity() * (each.getTax()+100)/100d)));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void save(Long invoiceId, InvoiceProductDto invoiceProductDto) {

    }

    @Override
    public void delete(Long invoiceProductId) {

    }

    @Override
    public void completeApprovalProcedures(Long invoiceId, InvoiceType type) {

    }

    @Override
    public boolean checkProductQuantity(InvoiceProductDto salesInvoiceProduct) {
        return false;
    }

    @Override
    public List<InvoiceProduct> findInvoiceProductsByInvoiceTypeAndProductRemainingQuantity(InvoiceType type, Product product, Integer remainingQuantity) {
        return null;
    }

    @Override
    public List<InvoiceProduct> findAllInvoiceProductsByProductId(Long id) {
        return null;
    }

}
