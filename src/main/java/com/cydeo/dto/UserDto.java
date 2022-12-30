package com.cydeo.dto;


import lombok.*;

import javax.validation.constraints.*;



@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    Long id;

    @NotBlank (message = "User Name is a required field.")
    @Email(message = "Email is a required field.")
    String username;

    @NotBlank (message = "Password is a required field.")
    @Pattern(regexp = "(?=.\\d)(?=.[a-z])(?=.*[A-Z]).{4,}", message = "Password should have at least 4 characters.")
    String password;

    @NotBlank (message = "Password needs to match")

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

    @NotNull (message = "Please select a role")
    RoleDto role;

    @NotNull(message = "Please select a customer")
    CompanyDto company;

    Boolean isOnlyAdmin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public Boolean getIsOnlyAdmin() {
        return isOnlyAdmin;
    }

    public void setIsOnlyAdmin(Boolean onlyAdmin) {
        isOnlyAdmin = onlyAdmin;
    }
}
