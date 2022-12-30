package com.cydeo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    Long id;

    @NotBlank (message = "User Name is a required field.")
    @Email(message = "Email is a required field.")
    String username;

    @NotBlank (message = "Password is a required field.")
    @Pattern(regexp = "(?=.\\d)(?=.[a-z])(?=.*[A-Z]).{4,}")
    String password;

    @NotBlank (message = "Password needs to match")
    @Pattern(regexp = "(?=.\\d)(?=.[a-z])(?=.*[A-Z]).{4,}")
    String confirmPassword;

    @NotBlank(message = "First Name is a required field.")
    @Size(max = 50, min = 2, message = "First Name should be 2-50 characters long.")
    String firstname;

    @NotBlank(message = "Last Name is a required field.")
    @Size(max = 50, min = 2, message = "Last Name should be 2-50 characters long.")
    String lastname;
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" // +111 (202) 555-0125  +1 (202) 555-0125
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"                                  // +111 123 456 789
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", message = "Phone number is required field and may be in any valid phone number format.")
    String phone;

    @NotBlank (message = "Please select a role")
    RoleDto role;
    @NotBlank (message = "Please select a customer")
    CompanyDto company;

    Boolean isOnlyAdmin;





}
