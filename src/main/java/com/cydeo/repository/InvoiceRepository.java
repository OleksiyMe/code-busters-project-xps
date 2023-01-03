package com.cydeo.repository;

import com.cydeo.entity.Company;
import com.cydeo.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findById(Long id);

    @Query("SELECT max(c.invoiceNo) from Invoice c where c.invoiceType = 'PURCHASE' and c.company.id = ?1 ")
    String findMaxId(Long id);
}
