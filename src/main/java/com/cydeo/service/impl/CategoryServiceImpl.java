package com.cydeo.service.impl;

import com.cydeo.dto.CategoryDto;
import com.cydeo.entity.Category;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CategoryRepository;
import com.cydeo.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MapperUtil mapperUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperUtil mapperUtil) {
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public CategoryDto findById(Long id) {
        return mapperUtil.convert(categoryRepository.findById(id), new CategoryDto());
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
    Category category=mapperUtil.convert(categoryDto, new Category());

        return mapperUtil.convert(categoryRepository.save(category), new CategoryDto());
    }
}
