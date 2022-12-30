package com.cydeo.controller;

import com.cydeo.dto.UserDto;
import com.cydeo.service.CompanyService;
import com.cydeo.service.RoleService;
import com.cydeo.service.SecurityService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")

public class UserController {
    private final UserService userService;
    private final SecurityService securityService;
    private final CompanyService companyService;
    private final RoleService roleService;


    public UserController(UserService userService, SecurityService securityService, CompanyService companyService, RoleService roleService) {
        this.userService = userService;
        this.securityService = securityService;
        this.companyService = companyService;
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public String listAllUsers(Model model) {
        UserDto loggedInUser = securityService.getLoggedInUser();
        model.addAttribute("users",
                userService.findAllFilterForLoggedInUser(loggedInUser));
        return "/user/user-list";
    }
    @GetMapping("/create")
    public String createUser(Model model) {

        model.addAttribute("newUser", new UserDto());
        model.addAttribute("companies",
                companyService.listAllCompaniesFilterForLoggedUser());
        model.addAttribute("userRoles",
                roleService.getRolesFilterForLoggedUser());
        return "/user/user-create";
    }

    @PostMapping("/create")
    public String createUserFinish(@ModelAttribute("newUser") UserDto userDto) {

        userService.save(userDto);
        return "redirect:/user/list";
    }


    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long userId, Model model) {

        model.addAttribute("user", userService.findById(userId));
        model.addAttribute("companies",
                companyService.listAllCompaniesFilterForLoggedUser());
        model.addAttribute("userRoles",
                roleService.getRolesFilterForLoggedUser());
        return "/user/user-update";
    }

    @PostMapping("/update/{id}")
    public String updateUserFinish(@PathVariable("id") Long userId,
                                   @ModelAttribute("user") UserDto userDtoToSave, Model model) {
        UserDto userDto =userService.findById(userId);
        userDtoToSave.setIsOnlyAdmin(userDto.getIsOnlyAdmin());
        userService.save(userDtoToSave);
        return "redirect:/users/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {

        userService.deleteUserById(id);
        return "redirect:/users/list";
    }
}
