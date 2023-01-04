package com.cydeo.service.impl;

import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.*;
import com.cydeo.enums.InvoiceType;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.InvoiceProductRepository;
import com.cydeo.service.InvoiceProductService;
import com.cydeo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final SecurityService securityService;
    private final MapperUtil mapperUtil;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository, SecurityService securityService, MapperUtil mapperUtil) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.securityService = securityService;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public InvoiceProductDto findInvoiceProductById(long id) {
        return null;
    }

    @Override
    public List<InvoiceProductDto> getInvoiceProductsOfInvoice(Long invoiceId) {
        return null;
    }

    @Override
    public void save(Long invoiceId, InvoiceProductDto invoiceProductDto) {

    }

    @Override
    public void delete(Long invoiceProductId) {

    }

    @Override
    public void deleteIpByInvoiceId(Long id) {
        UserDto loggedInUser = securityService.getLoggedInUser(); //got the logged in User

        List<InvoiceProduct> invoiceProductList=invoiceProductRepository.findAllByInvoice_Id(id);//found all the related InvoiceProducts

        invoiceProductList.stream().filter(invoiceProduct -> invoiceProduct.getInvoice().getCompany().getId()
                .equals(loggedInUser.getCompany().getId())).forEach(invoiceProduct->delete(invoiceProduct.getId())) ;
        //if Id of invoiceProducts of that Invoice match the Id of that logged in user's Company Id then delete each of the Invoice Products

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
