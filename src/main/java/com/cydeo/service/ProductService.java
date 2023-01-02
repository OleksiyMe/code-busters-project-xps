package com.cydeo.service;

import com.cydeo.dto.ProductDto;
import com.cydeo.enums.ProductUnit;

import java.util.List;

public interface ProductService {


    ProductDto findProductById(Long Id);

    List<ProductDto> listAllProducts();

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto);

    void deleteProductById(Long id);

    List<ProductUnit> listAllProductUnits();


    Boolean productListedInInvoice(Long productId);


}


