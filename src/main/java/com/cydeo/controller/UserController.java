package com.cydeo.controller;

import com.cydeo.dto.UserDto;
import com.cydeo.service.SecurityService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")

public class UserController {
    private final UserService userService;
    private final SecurityService securityService;


    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }
    @GetMapping("/list")
    public String listAllUsers(Model model){
        UserDto loggedInUser=securityService.getLoggedInUser();

        model.addAttribute("users",
                userService.findAllFilterForLoggedInUser(loggedInUser));

        return"/user/user-list";

    }
}
