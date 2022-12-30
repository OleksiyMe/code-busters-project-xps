package com.cydeo.dto;

import com.cydeo.enums.ProductUnit;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    Long id;
    String name;
    Integer quantityInStock;
    Integer lowLimitAlert;
    ProductUnit productUnit;
    CategoryDto category;

}
