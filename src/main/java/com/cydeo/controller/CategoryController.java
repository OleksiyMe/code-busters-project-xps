package com.cydeo.controller;

import com.cydeo.dto.CategoryDto;
import com.cydeo.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")

    public String listAllCategories(Model model) {
        model.addAttribute("categories", categoryService.listAllCategories());

        return "/category/category-list";
    }

    @GetMapping("/create")
    public String createCategory(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return "/category/category-create";
    }

    @PostMapping("/create")
    public String createNewCategory(@Valid @ModelAttribute("categoryDto") CategoryDto categoryDto,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/category/category-create";
        }
        categoryService.save(categoryDto);
        return "redirect:/categories/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {

        categoryService.delete(id);
        return "redirect:/categories/list";
    }


}
