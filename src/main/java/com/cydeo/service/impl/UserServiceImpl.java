package com.cydeo.service.impl;

import com.cydeo.dto.UserDto;
import com.cydeo.entity.User;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.SecurityService;
import com.cydeo.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final SecurityService securityService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           MapperUtil mapperUtil, @Lazy SecurityService securityService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User not found"));
        return mapperUtil.convert(user, new UserDto());
    }

    private List<UserDto> findAllOrderByCompanyAndRole() {

        List<UserDto> list = userRepository
                .findAllNotDeletedWithActiveCompanyOrderByCompanyAndRole().stream()
                .map(currentUser -> {
                    Boolean isOnlyAdmin =
                            currentUser.getRole().getDescription().equals("Admin");
                    UserDto userDto = mapperUtil.convert(currentUser, new UserDto());
                    userDto.setIsOnlyAdmin(isOnlyAdmin);
                    return userDto;
                })
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<UserDto> findAllFilterForLoggedInUser() {
        UserDto loggedInUser = securityService.getLoggedInUser();
        switch (loggedInUser.getRole().getDescription()) {
            case "Root User":
                return findAllOrderByCompanyAndRole().stream()
                        .filter(user -> user.getRole().getDescription().equals("Admin"))
                        .collect(Collectors.toList());
            case "Admin":
                return findAllOrderByCompanyAndRole().stream()
                        .filter(user -> user.getCompany().getId().equals(loggedInUser.getCompany().getId()))
                        .collect(Collectors.toList());
            default:
                return findAllOrderByCompanyAndRole();

        }

    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).get();
        user.setIsDeleted(true);
        user.setUsername(user.getUsername() + "-" + user.getId());
        userRepository.save(user);
    }

    @Override
    public void update(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).get();
        User convertedUser = mapperUtil.convert(userDto, new User());
        convertedUser.setId(user.getId());
        convertedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        convertedUser.setEnabled(user.isEnabled());
        userRepository.save(convertedUser);
    }


    @Override
    public UserDto findById(Long userId) {
        return mapperUtil.convert(
                userRepository.findById(userId), new UserDto()
        );
    }

    @Override
    public UserDto save(UserDto userDto) {

        User user = mapperUtil.convert(userDto, new User());
//temporary
        user.setEnabled(true);
        userRepository.save(user);
        return mapperUtil.convert(user, userDto);

    }


    @Override
    public boolean emailExists(UserDto userDto) {
        Optional<User> userWeCreate =
                userRepository.findByUsername(userDto.getUsername());

        if (!userWeCreate.isPresent()) return false;

        return !userWeCreate.get().getId().equals(userDto.getId());

    }

    @Override
    public String userCanNotBeDeleted(Long id) {
        UserDto loggedInUser = securityService.getLoggedInUser();
        Optional<User> userToBeDeletedOptional = userRepository.findNotDeletedById(id);
        User userToBeDeleted = new User();
        if (userToBeDeletedOptional.isPresent())
            userToBeDeleted = userToBeDeletedOptional.get();
        else return "!!ERROR!!: There is no user with id " + id;
        if (userToBeDeleted.getRole().getDescription().equals("Root User"))
            return "!!ERROR!!: Only God can delete Root User :)";
        if (!userToBeDeleted.getRole().getDescription().equals("Admin") &&
                loggedInUser.getRole().getDescription().equals("Root User"))
            return "!!ERROR!!: As a Root User you can only create and delete Admin users";
        if (userToBeDeleted.getRole().getDescription().equals("Admin") &&
                !loggedInUser.getRole().getDescription().equals("Root User"))
            return "!!ERROR!!: Only Root User can delete Admin user. And your role is "
                    +loggedInUser.getRole().getDescription();
        if(!userToBeDeleted.getCompany().getId().equals(loggedInUser.getCompany().getId()) &&
        !loggedInUser.getRole().getDescription().equals("Root User"))
            return "!!ERROR!!: As Admin user you can delete managers and employees only from " +
                    "your own company";
        //return not empty string -- user can not be deleted

        return "";  //empty string -- user can be deleted
    }
}



