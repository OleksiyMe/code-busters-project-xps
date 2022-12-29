package com.cydeo.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto {

    Long id;
    @NotBlank
    @Size(max = 100, min = 2)
    String description;
    CompanyDto company;
    Boolean hasProduct;

}
