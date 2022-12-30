package com.cydeo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    Long id;
    String addressLine1;
    String addressLine2;
    String city;
    String state;
    String country;
    String zipCode;

}
