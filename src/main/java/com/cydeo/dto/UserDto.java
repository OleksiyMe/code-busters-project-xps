package com.cydeo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    Long id;
    String username;
    String password;
    String confirmPassword;
    String firstname;
    String lastname;
    String phone;
    RoleDto role;
    CompanyDto company;
    Boolean isOnlyAdmin;

}
