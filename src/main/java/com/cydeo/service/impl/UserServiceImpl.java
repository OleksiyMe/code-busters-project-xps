package com.cydeo.service.impl;

import com.cydeo.dto.UserDto;
import com.cydeo.entity.User;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.SecurityService;
import com.cydeo.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository,
                           MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow( () -> new NoSuchElementException("User not found"));
        return mapperUtil.convert(user, new UserDto());
    }

    @Override
    public List<UserDto> findAllOrderByCompanyOrderByRole() {

        List<UserDto> list = userRepository.findByIsDeletedOrderByCompanyOrderByRole(false).stream()
                .map(currentUser-> mapperUtil.convert(currentUser, new UserDto()))
                .collect(Collectors.toList());
        return list;
    }

}
