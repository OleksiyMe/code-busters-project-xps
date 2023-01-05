package com.cydeo.controller;

import com.cydeo.dto.UserDto;
import com.cydeo.service.CompanyService;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")

public class UserController {
    private final UserService userService;
    private final CompanyService companyService;
    private final RoleService roleService;


    public UserController(UserService userService, CompanyService companyService, RoleService roleService) {
        this.userService = userService;
        this.companyService = companyService;
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public String listAllUsers(Model model) {
        model.addAttribute("users",
                userService.findAllFilterForLoggedInUser());
        return "/user/user-list";
    }

    @GetMapping("/create")
    public String createUser(Model model) {

        model.addAttribute("newUser", new UserDto());
        model.addAttribute("companies",
                companyService.listAllActiveCompaniesForLoggedInUser());
        model.addAttribute("userRoles",
                roleService.getRolesFilterForLoggedUser());
        return "/user/user-create";
    }

    @PostMapping("/create")
    public String createUserFinish(@Valid @ModelAttribute("newUser") UserDto userDto, BindingResult bindingResult, Model model) {

        boolean emailExist = userService.emailExists(userDto);

        if (bindingResult.hasErrors() || emailExist) {
            if (emailExist) {
                bindingResult.rejectValue("username", " ", "A user with this email already exists. Please try with different email.");
            }
            model.addAttribute("newUser", userDto);
            model.addAttribute("companies",
                    companyService.listAllActiveCompaniesForLoggedInUser());
            model.addAttribute("userRoles",
                    roleService.getRolesFilterForLoggedUser());

            return "/user/user-create";
        }

        userService.save(userDto);
        return "redirect:/users/list";
    }


    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long userId, Model model) {

        model.addAttribute("user", userService.findById(userId));
        model.addAttribute("companies",
                companyService.listAllActiveCompaniesForLoggedInUser());
        model.addAttribute("userRoles",
                roleService.getRolesFilterForLoggedUser());
        return "/user/user-update";
    }

    @PostMapping("/update/{id}")
    public String updateUserFinish(@PathVariable("id") Long userId,
                                   @ModelAttribute("user") UserDto userDtoToSave, BindingResult bindingResult, Model model) {
        boolean emailExist = userService.emailExists(userDtoToSave);

        if (bindingResult.hasErrors() || emailExist) {
            if (emailExist) {
                bindingResult.rejectValue("username", " ", "A user with this email already exists. Please try with different email.");
            }
            UserDto userDto = userService.findById(userId);
            userDtoToSave.setIsOnlyAdmin(userDto.getIsOnlyAdmin());
            userService.save(userDtoToSave);
            return "redirect:/users/list";
        }
        userService.update(userDtoToSave);
        return "redirect:/users/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,  Model model) {
        String errormessage = userService.userCanNotBeDeleted(id);
        if (!errormessage.isBlank()) {
            model.addAttribute("users",
                    userService.findAllFilterForLoggedInUser());
            model.addAttribute("errorMessage", errormessage);
            return "/user/user-list";
        }

        userService.deleteUserById(id);
        return "redirect:/users/list";
    }
}

