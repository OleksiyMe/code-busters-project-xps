package com.cydeo.service;

import com.cydeo.dto.ProductDto;
import com.cydeo.dto.RoleDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {


    RoleDto findRoleById(Long Id);


    List<RoleDto> getAllRoles();

    List<RoleDto> getRolesFilterForLoggedUser();
}
