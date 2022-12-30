package com.cydeo.dto;

import com.cydeo.entity.InvoiceProduct;
import com.cydeo.enums.InvoiceStatus;
import com.cydeo.enums.InvoiceType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {

    Long id;
    String invoiceNo;
    InvoiceStatus invoiceStatus;
    InvoiceType invoiceType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;

    CompanyDto company;
    ClientVendorDto clientVendor;
    BigDecimal price;
    Integer tax;
    BigDecimal total;
    List<InvoiceProduct> invoiceProducts;

}
