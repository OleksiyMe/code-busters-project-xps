package com.cydeo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    Long id;

    @NotNull(message = "Address is a required field")
    @Size(max = 100, min = 2, message = "Address should be 2-100 characters long.")
    String addressLine1;

    @Size(max = 100, message = "Address should be maximum 100 characters long.")
    String addressLine2;

    @NotNull(message = "City is a required field")
    @Size(max = 50, min = 2, message = "City should be 2-50 characters long.")
    String city;

    @NotNull(message = "State is a required field")
    @Size(max = 50, min = 2, message = "State should be 2-50 characters long.")
    String state;

    @NotNull(message = "Country is a required field")
    String country;

    @NotNull(message = "Zipcode is a required field")
    @Pattern(regexp = "^\\d{5}-\\d{4}$",
            message = "Zipcode should have a valid format. format " +
                    "\"xxxxx-xxxx\" 5 digit + dash + 4 digit")
    String zipCode;

}
