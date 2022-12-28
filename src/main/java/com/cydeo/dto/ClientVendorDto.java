package com.cydeo.dto;

import com.cydeo.enums.ClientVendorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
