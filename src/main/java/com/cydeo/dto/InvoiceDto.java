package com.cydeo.dto;

import com.cydeo.entity.InvoiceProduct;
import com.cydeo.enums.InvoiceStatus;
import com.cydeo.enums.InvoiceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {

    Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String invoiceNo;
    InvoiceStatus invoiceStatus;
    InvoiceType invoiceType;

//    @DateTimeFormat(pattern = "MMMM dd, y")
    LocalDate date;

    CompanyDto company;

    @NotNull(message = "This is a required field.")
    @Valid
    ClientVendorDto clientVendor;

    BigDecimal price;
    BigDecimal tax;
    BigDecimal total;
    List<InvoiceProductDto> invoiceProducts;

}
