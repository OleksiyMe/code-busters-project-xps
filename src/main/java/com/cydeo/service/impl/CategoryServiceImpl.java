package com.cydeo.service.impl;

import com.cydeo.dto.CategoryDto;
import com.cydeo.entity.Category;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CategoryRepository;
import com.cydeo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MapperUtil mapperUtil;


    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperUtil mapperUtil) {
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public CategoryDto findCategoryById(Long id) {
        return mapperUtil.convert(categoryRepository.getCategoryById(id), new CategoryDto());
    }

    @Override
    public List<CategoryDto> listAllCategories() {


        return categoryRepository.findAll().stream().map(category->mapperUtil.convert(category, new CategoryDto())).sorted( Comparator.comparing(CategoryDto::getDescription)).collect(Collectors.toList());
    }


    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category=mapperUtil.convert(categoryDto, new Category());

        return mapperUtil.convert(categoryRepository.save(category), new CategoryDto());
    }

    @Override
    public void edit(CategoryDto category) {

    }

    @Override
    public void delete(Long id) {

        Category category = categoryRepository.getCategoryById(id).get();

        category.setIsDeleted(true);

        categoryRepository.save(category);

        //where clause ??? we added it to Category Entity?


    }


}
