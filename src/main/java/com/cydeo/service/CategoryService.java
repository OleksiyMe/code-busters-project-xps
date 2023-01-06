package com.cydeo.service;

import com.cydeo.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto findCategoryById(Long id);
    List<CategoryDto> listAllNotDeletedCategoriesForCurrentCompany();
    CategoryDto save(CategoryDto categoryDto);
    boolean isDescriptionExist(String description);
    CategoryDto update(CategoryDto categoryDto);
    void delete(Long Id);   //Soft Delete
    public String categoryCanNotBeDeleted(Long id);
}
