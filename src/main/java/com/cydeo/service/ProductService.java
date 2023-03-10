package com.cydeo.service;

import com.cydeo.dto.ProductDto;
import com.cydeo.enums.ProductUnit;

import java.util.List;

public interface ProductService {


    ProductDto findProductById(Long Id);

    List<ProductDto> listAllNotDeletedProductsForCurrentCompany();
    List<ProductDto> listAllNotDeletedProducts();

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto);

    void deleteProductById(Long id);

    List<ProductUnit> listAllProductUnits();


    String productCanNotBeDeleted(Long productId);


    void save(ProductDto productDto);

    Boolean productNameExists(ProductDto productDto);
}


