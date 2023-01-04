package com.cydeo.entity;

import com.cydeo.entity.common.BaseEntity;
import com.cydeo.enums.InvoiceStatus;
import com.cydeo.enums.InvoiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")

public class Invoice extends BaseEntity {

    String invoiceNo;

    @Enumerated(EnumType.STRING)
    InvoiceStatus invoiceStatus;

    @Enumerated(EnumType.STRING)
    InvoiceType invoiceType;

    LocalDate date;

    @ManyToOne
    @JoinColumn(name = "client_vendor_id")
    ClientVendor clientVendor;

    @ManyToOne
    @JoinColumn(name = "company_id")
    Company company;
}
