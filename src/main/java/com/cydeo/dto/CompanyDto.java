package com.cydeo.dto;

import com.cydeo.enums.CompanyStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    Long id;
    String title;
    String phone;
    String website;
    AddressDto address;
    CompanyStatus companyStatus;

}
