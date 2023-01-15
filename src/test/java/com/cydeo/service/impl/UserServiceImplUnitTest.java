package com.cydeo.service.impl;

import com.cydeo.dto.*;
import com.cydeo.entity.Company;
import com.cydeo.entity.Role;
import com.cydeo.entity.User;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.SecurityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplUnitTest {

    @Mock
    UserRepository userRepository;
    @Mock
    MapperUtil mapperUtil;
    @Mock
    SecurityService securityService;
    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userService;


    @Test
    void findByUsername() {
//Given
        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.of(new User()));
        when(mapperUtil.convert(any(User.class), any(UserDto.class)))
                .thenReturn(new UserDto());

//When
        UserDto userDto = userService.findByUsername("abc");
//Then
        //check the order of calling for these two mocks
        InOrder inOrder = inOrder(userRepository, mapperUtil);
        //the order of calling should be this:
        inOrder.verify(userRepository).findByUsername(any(String.class));
        inOrder.verify(mapperUtil).convert(any(User.class), any(UserDto.class));
        //are we getting something real at the end
        Assertions.assertNotNull(userDto);
    }

    @Test
    void findAllFilterForLoggedInUser_Test() {

    }


    @Test
    void deleteUserById() {
//Given
        User user =new User();
        user.setId(1L);
        user.setUsername("username");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

//When
        userService.deleteUserById(1L);
//Then
        //check the order of calling for these two mocks
        InOrder inOrder = inOrder(userRepository);
        //the order of calling should be this:
        inOrder.verify(userRepository).findById(anyLong());
        inOrder.verify(userRepository).save(any(User.class));

        //checking that object user was changed in userService.deleteUserById() method
        Assertions.assertEquals(user.getIsDeleted(),true);
        Assertions.assertEquals(user.getUsername(),"username-1");
    }


    @Test
    void update() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void emailExists() {
    }

    @Test
    void userCanNotBeDeleted() {
    }

    @Test
    void passwordsDoNotMatch() {
    }
}