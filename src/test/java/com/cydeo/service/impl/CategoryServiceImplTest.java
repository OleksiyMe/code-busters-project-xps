package com.cydeo.service.impl;

import com.cydeo.dto.AddressDto;
import com.cydeo.dto.CategoryDto;
import com.cydeo.dto.CompanyDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.Address;
import com.cydeo.entity.Category;
import com.cydeo.entity.Company;
import com.cydeo.enums.CompanyStatus;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    MapperUtil mapperUtil;

    @Mock
    SecurityServiceImpl securityService;

    @Mock
    CompanyServiceImpl companyService;

    @InjectMocks
    CategoryServiceImpl categoryServiceImpl;

    @Test
    void findCategoryById_test() {
        //stubbing
        when(categoryRepository.getCategoryById(anyLong())).thenReturn(Optional.of(new Category()));
        when(mapperUtil.convert(any(Category.class), any(CategoryDto.class))).thenReturn(new CategoryDto());

        //actual method call
        CategoryDto categoryDto = categoryServiceImpl.findCategoryById(anyLong());

        //then
        InOrder inOrder = inOrder(categoryRepository, mapperUtil);

        inOrder.verify(categoryRepository).getCategoryById(anyLong());
        inOrder.verify(mapperUtil).convert(any(Category.class), any(CategoryDto.class));

        assertNotNull(categoryDto);

    }

    @Test
    void listAllNotDeletedCategoriesForCurrentCompany_test() {

//        UserDto loggedInUser = new UserDto(1L, "Username@test.com", "Abc22#", "Abc22@", "Alex",
//                "First", "1234567890", null, new CompanyDto(1L, "CompanyName", "1234567890",
//                "http//www.vvv.com", new AddressDto(), CompanyStatus.ACTIVE),
//                true);
//
//        Company company = new Company("CompanyName", "1234567890",
//                "http//www.vvv.com", CompanyStatus.ACTIVE, new Address());
//
//        when(securityService.getLoggedInUser()).thenReturn(loggedInUser);
//        when(companyService.findCompanyById(anyLong())).thenReturn(new CompanyDto());
////        when(mapperUtil.convert(any(CompanyDto.class), any(Company.class))).thenReturn(company);
//        when(mapperUtil.convert(any(Category.class), any(CategoryDto.class))).thenReturn(new CategoryDto());
//
//        List<CategoryDto> categories = categoryServiceImpl.listAllNotDeletedCategoriesForCurrentCompany();
//
//        InOrder inOrder = inOrder(categoryRepository, mapperUtil);
//
//        inOrder.verify(companyService.findCompanyById(anyLong()));
////        inOrder.verify(mapperUtil.convert(any(CompanyDto.class), any(Company.class)));
//        inOrder.verify(categoryRepository).findAllNotDeleted();
//        inOrder.verify(mapperUtil).convert(any(Category.class), any(CategoryDto.class));
//
//        Assertions.assertEquals(loggedInUser.getCompany(), company);
//        assertNotNull(categories);
    }

    @Test
    void save_test() {
    }

    @Test
    void isDescriptionExist_test() {


    }

    @Test
    void update_test() {
    }

    @Test
    void delete() {

        Category category = new Category();
        category.setIsDeleted(false);

        when(categoryRepository.getCategoryById(anyLong())).thenReturn(Optional.of(category));

        categoryServiceImpl.delete(anyLong());

        inOrder(categoryRepository).verify(categoryRepository).getCategoryById(anyLong());

        Assertions.assertTrue(category.getIsDeleted());

    }

    @Test
    void categoryCanNotBeDeleted_test() {
    }
}