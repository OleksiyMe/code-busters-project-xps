package com.cydeo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    Long id;
    String addressLine1;
    String addressLine2;
    String city;
    String state;
    String country;
    String zipcode;

}
