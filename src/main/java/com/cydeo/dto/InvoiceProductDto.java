package com.cydeo.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceProductDto {

    Long id;
    Integer quantity;
    BigDecimal price;
    Integer tax;
    BigDecimal total;
    BigDecimal profitLoss;
    Integer remainingQty;
    InvoiceDto invoice;
    ProductDto product;

}
