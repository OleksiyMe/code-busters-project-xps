package com.cydeo.service.impl;

import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.*;
import com.cydeo.entity.InvoiceProduct;
import com.cydeo.entity.Product;
import com.cydeo.enums.InvoiceType;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.InvoiceProductRepository;
import com.cydeo.service.InvoiceProductService;
import com.cydeo.service.InvoiceService;
import com.cydeo.service.ProductService;
import com.cydeo.service.SecurityService;
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
    private final SecurityService securityService;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository,
                                     @Lazy InvoiceService invoiceService, ProductService productService,
                                     MapperUtil mapperUtil, SecurityService securityService) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.invoiceService = invoiceService;
        this.productService = productService;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
    }

    @Override
    public InvoiceProductDto findInvoiceProductById(long id) {
        return mapperUtil.convert(invoiceProductRepository.findInvoiceProductById(id), new InvoiceProductDto());
    }

    @Override
    public List<InvoiceProductDto> getInvoiceProductsByInvoiceId(Long invoiceId) {
        return invoiceProductRepository.findAllByInvoice_Id(invoiceId)
                .stream()
                .sorted(Comparator.comparing((InvoiceProduct each) -> each.getInvoice().getInvoiceNo()).reversed())
                .map(each -> {
                    InvoiceProductDto dto = mapperUtil.convert(each, new InvoiceProductDto());
                    dto.setTotal(each.getPrice().multiply(BigDecimal.valueOf(each.getQuantity() * (each.getTax() + 100) / 100d)));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void save(Long invoiceId, InvoiceProductDto invoiceProductDto) {
        Invoice invoice = mapperUtil.convert(invoiceService.findInvoiceById(invoiceId), new Invoice());
        InvoiceProduct invoiceProduct = mapperUtil.convert(invoiceProductDto, new InvoiceProduct());
        invoiceProduct.setInvoice(invoice);
        invoiceProduct.setProfitLoss(BigDecimal.ZERO);
        invoiceProductRepository.save(invoiceProduct);

    }

    @Override
    public void SoftDelete(Long invoiceProductId) {

        InvoiceProduct invoiceProduct = mapperUtil.convert(findInvoiceProductById(invoiceProductId),
                new InvoiceProduct());
        invoiceProduct.setIsDeleted(true);
        invoiceProductRepository.save(invoiceProduct);
    }

    @Override
    public void deleteIpByInvoiceId(Long id) {
        UserDto loggedInUser = securityService.getLoggedInUser(); //got the logged in User

        List<InvoiceProduct> invoiceProductList = invoiceProductRepository.findAllByInvoice_Id(id);//found all the related InvoiceProducts

        invoiceProductList.stream().filter(invoiceProduct -> invoiceProduct.getInvoice().getCompany().getId()
                .equals(loggedInUser.getCompany().getId())).forEach(invoiceProduct -> SoftDelete(invoiceProduct.getId()));
        //if Id of invoiceProducts of that Invoice match the Id of that logged in user's Company Id then delete each of the Invoice Products

    }

    @Override
    public void completeApprovalProcedures(Long invoiceId, InvoiceType type) {

    }

    @Override
    public boolean checkProductQuantity(InvoiceProductDto salesInvoiceProduct) {
        return salesInvoiceProduct.getProduct().getQuantityInStock() >= salesInvoiceProduct.getQuantity();
    }

    @Override
    public List<InvoiceProduct> findInvoiceProductsByInvoiceTypeAndProductRemainingQuantity(InvoiceType type, Product product, Integer remainingQuantity) {
        return invoiceProductRepository.findInvoiceProductsByInvoiceInvoiceTypeAndProductAndRemainingQuantityNotOrderByIdAsc(type, product, remainingQuantity);
    }

    @Override
    public List<InvoiceProduct> findAllInvoiceProductsByProductId(Long id) {
        return invoiceProductRepository.findAllInvoiceProductByProductId(id);
    }

    @Override
    public List<InvoiceProductDto> findAllNotDeleted() {
        return invoiceProductRepository.findAll().stream()
                .filter(invoiceProduct -> invoiceProduct.getIsDeleted().equals(false))
                .map(invoiceProduct -> mapperUtil.convert(invoiceProduct, new InvoiceProductDto()))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceProduct> FindAllInvoiceProducts() {
        return invoiceProductRepository.findAll();
    }

}
