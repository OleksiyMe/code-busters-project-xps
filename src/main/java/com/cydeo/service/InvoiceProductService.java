package com.cydeo.service;

import com.cydeo.dto.InvoiceProductDto;
import com.cydeo.entity.InvoiceProduct;
import com.cydeo.entity.Product;
import com.cydeo.enums.InvoiceType;

import java.util.List;

public interface InvoiceProductService {

    InvoiceProductDto findInvoiceProductById(long id);
    List<InvoiceProductDto> getInvoiceProductsByInvoiceId(Long invoiceId);
    void save(Long invoiceId, InvoiceProductDto invoiceProductDto);
    void SoftDelete(Long invoiceProductId);
    void deleteIpByInvoiceId(Long id); //uo
    void completeApprovalProcedures(Long invoiceId, InvoiceType type);
    boolean checkProductQuantity(InvoiceProductDto salesInvoiceProduct);
    List<InvoiceProduct> findInvoiceProductsByInvoiceTypeAndProductRemainingQuantity(InvoiceType type, Product product, Integer remainingQuantity);
    List<InvoiceProduct> findAllInvoiceProductsByProductId(Long id);

    List<InvoiceProductDto> findAllNotDeleted();

    List<InvoiceProduct> FindAllInvoiceProducts();

   List<InvoiceProductDto> findAllNotDeletedForCurrentCompany();
    List<InvoiceProductDto> findAllNotDeletedForCurrentCompanySortByDate();
}
