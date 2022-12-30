package com.cydeo.dto;

import lombok.*;

@Getter
@Setter
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
