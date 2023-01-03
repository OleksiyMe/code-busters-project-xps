package com.cydeo.service.impl;

import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.entity.*;
import com.cydeo.enums.InvoiceType;
import com.cydeo.repository.InvoiceProductRepository;
import com.cydeo.service.InvoiceProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {


    private final InvoiceProductRepository invoiceProductRepository;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository) {
        this.invoiceProductRepository = invoiceProductRepository;
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

        List<InvoiceProduct> invoiceProductList=invoiceProductRepository.findAllByInvoice_Id(id);//uo

        invoiceProductList.stream().forEach(invoiceProduct->delete(invoiceProduct.getId())) ;   //uo

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
