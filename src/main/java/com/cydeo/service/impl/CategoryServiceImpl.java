package com.cydeo.service.impl;

import com.cydeo.dto.CategoryDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.Category;
import com.cydeo.entity.Company;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CategoryRepository;
import com.cydeo.service.CategoryService;
import com.cydeo.service.CompanyService;
import com.cydeo.service.ProductService;
import com.cydeo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MapperUtil mapperUtil;
    private final SecurityService securityService;
    private final CompanyService companyService;
    private final ProductService productService;


    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperUtil mapperUtil, SecurityService securityService, CompanyService companyService, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
        this.companyService = companyService;
        this.productService = productService;
    }


    @Override
    public CategoryDto findCategoryById(Long id) {
        return mapperUtil.convert(categoryRepository.getCategoryById(id).get(), new CategoryDto());
    }

    @Override
    public List<CategoryDto> listAllNotDeletedCategoriesForCurrentCompany() {
        Company currentCompany = mapperUtil.convert(
                securityService.getLoggedInUser().getCompany(), new Company());

        return categoryRepository.findAllNotDeleted().stream()
                .filter(category -> category.getCompany().getId().equals(currentCompany.getId()))
                .map(category -> mapperUtil.convert(category, new CategoryDto()))
                .sorted(Comparator.comparing(CategoryDto::getDescription))
                .collect(Collectors.toList());
    }


    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = mapperUtil.convert(categoryDto, new Category());
        UserDto loggedInUser = securityService.getLoggedInUser();
        category.setCompany(
                mapperUtil.convert(loggedInUser.getCompany(), new Company()));
        category.setInsertUserId(loggedInUser.getId());
        category.setLastUpdateUserId(loggedInUser.getId());
        category.setLastUpdateDateTime(LocalDateTime.now());
        category.setInsertDateTime(LocalDateTime.now());

        return mapperUtil.convert(categoryRepository.save(category), new CategoryDto());
    }

    @Override
    public boolean isDescriptionExist(String description) {
        return categoryRepository.existsByDescriptionAndCompany_Title(description,companyService.getCompanyDto().getTitle());
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        Category category = categoryRepository.getCategoryById(categoryDto.getId()).get();
        category.setDescription(categoryDto.getDescription());
        return mapperUtil.convert(categoryRepository.save(category), new CategoryDto());
    }

    @Override
    public void delete(Long id) {

        Category category = categoryRepository.getCategoryById(id).get();
        category.setIsDeleted(true);
        categoryRepository.save(category);

    }

    @Override
    public String categoryCanNotBeDeleted(Long id) {
        CategoryDto categoryDto = findCategoryById(id);
        if (productService.listAllNotDeletedProducts().stream()
                .anyMatch(productDto -> productDto.getCategory().getId().equals(id)))
            return "You can not delete this Category. It has products inside.";
//return not empty string -- we can not delete ClientVendor
//return empty string -- we can delete ClientVendor
        return "";
    }


}
