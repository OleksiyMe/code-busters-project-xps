package com.cydeo.service;

import com.cydeo.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto findByUsername(String username);

    List<UserDto> findAllFilterForLoggedInUser();

    void deleteUserById(Long id);

    UserDto findById(Long userId);

    UserDto save(UserDto userDto);

    boolean emailExists (UserDto userDto);
}
