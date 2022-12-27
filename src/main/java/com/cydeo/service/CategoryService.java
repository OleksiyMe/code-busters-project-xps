package com.cydeo.service;

import com.cydeo.dto.CategoryDto;

public interface CategoryService {
    CategoryDto findById(Long id);

    CategoryDto save(CategoryDto categoryDto);

}
