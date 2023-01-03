package com.cydeo.repository;

import com.cydeo.entity.Company;
import com.cydeo.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findById(Long id);
    @Query("select i from Invoice i where i.isDeleted=false order by i.invoiceNo")
    List<Invoice> findAllNotDeleted();

    @Query("SELECT max(c.invoiceNo) from Invoice c where c.invoiceType = 'PURCHASE' and c.company.id = ?1 ")
    String findMaxId(Long id);

//alternatives
//    Invoice findInvoiceById(Long id);
//    List<Invoice> findInvoicesByCompanyAndInvoiceType(Company company, InvoiceType invoiceType);
//    List<Invoice> findInvoicesByCompanyAndInvoiceStatus(Company company, InvoiceStatus invoiceStatus);
//    List<Invoice> findInvoicesByCompanyAndInvoiceStatusOrderByDateDesc(Company company, InvoiceStatus invoiceStatus);
//    Integer countAllByCompanyAndClientVendor_Id(Company company, Long clientVendorId);
}
