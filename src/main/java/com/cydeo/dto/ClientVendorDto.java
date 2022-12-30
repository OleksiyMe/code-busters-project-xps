package com.cydeo.dto;

import com.cydeo.enums.ClientVendorType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientVendorDto {

    Long id;
    String clientVendorName;
    String phone;
    String website;
    ClientVendorType clientVendorType;
    AddressDto address;
    CompanyDto company;

}
