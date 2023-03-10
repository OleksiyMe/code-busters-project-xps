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
      //  throw new RuntimeException("My Exception");
        model.addAttribute("categories", categoryService.listAllNotDeletedCategoriesForCurrentCompany());

        return "category/category-list";
    }

    @GetMapping("/create")
    public String createCategory(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return "category/category-create";
    }

    @PostMapping("/create")
    public String createNewCategory(@Valid @ModelAttribute("categoryDto") CategoryDto categoryDto,
                                    BindingResult bindingResult, Model model) {
        boolean descriptionExist = categoryService.isDescriptionExist(categoryDto.getDescription());
        if (descriptionExist) {
            bindingResult.rejectValue("description", " ",
                    "This category description already exist");
        }
        if (bindingResult.hasErrors()) {
            return "category/category-create";
        }
        categoryService.save(categoryDto);
        return "redirect:/categories/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, Model model) {
        String errormessage = categoryService.categoryCanNotBeDeleted(id);
        if (!errormessage.isBlank()) {
            model.addAttribute("categories",
                    categoryService.listAllNotDeletedCategoriesForCurrentCompany());
            model.addAttribute("errorMessage", errormessage);
            return "category/category-list";
        }



        categoryService.delete(id);
        return "redirect:/categories/list";
    }

    @GetMapping("/update/{categoryId}")
    public String editCategory(@PathVariable("categoryId") Long categoryId,Model model){

        model.addAttribute("category",categoryService.findCategoryById(categoryId));

        return "category/category-update";
    }
    @PostMapping("/update/{categoryId}")
    public String updateCategory(@Valid @ModelAttribute("category") CategoryDto categoryDto,BindingResult bindingResult, @PathVariable("categoryId") Long categoryId,Model model){
        categoryDto.setId(categoryId);

        boolean descriptionExist = categoryService.isDescriptionExist(categoryDto.getDescription());
        if (descriptionExist) {
            bindingResult.rejectValue("description", " ", "This category description already exist");
        }
        if(bindingResult.hasErrors()){

            return "category/category-update";
        }
        categoryService.update(categoryDto);

        return "redirect:/categories/list";

    }

}
