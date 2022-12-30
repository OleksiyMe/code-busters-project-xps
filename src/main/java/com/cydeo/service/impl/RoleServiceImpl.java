package com.cydeo.service.impl;

import com.cydeo.dto.RoleDto;
import com.cydeo.dto.UserDto;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.RoleRepository;
import com.cydeo.service.RoleService;
import com.cydeo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final SecurityService securityService;
    private final MapperUtil mapperUtil;

    public RoleServiceImpl(RoleRepository roleRepository, SecurityService securityService, MapperUtil mapperUtil) {
        this.roleRepository = roleRepository;
        this.securityService = securityService;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public RoleDto findRoleById(Long id) {

        return mapperUtil.convert(roleRepository.findById(id), new RoleDto());
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(role -> mapperUtil.convert(role, new RoleDto()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleDto> getRolesFilterForLoggedUser() {
        UserDto loggedInUser = securityService.getLoggedInUser();
        switch (loggedInUser.getRole().getDescription()) {
            case "Root User":
                return getAllRoles().stream()
                        .filter(role -> role.getDescription().equals("Admin"))
                        .collect(Collectors.toList());
            case "Admin":
                return getAllRoles().stream()
                        .filter(role -> !role.getDescription().equals("Admin")
                        &&!role.getDescription().equals("Root User"))
                        .collect(Collectors.toList());
            default:
                return getAllRoles();
        }
    }
}
