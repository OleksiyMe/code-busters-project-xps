package com.cydeo.dto;

import com.cydeo.enums.ProductUnit;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    Long id;

    @NotBlank
    @Size(max = 100, min = 2)
    String name;

    Integer quantityInStock;

    @NotNull
    @Min(value=1)
    Integer lowLimitAlert;

    @NotNull
    ProductUnit productUnit;

    @NotNull
    CategoryDto category;

}
