package com.cydeo.service.impl;

import com.cydeo.dto.ClientVendorDto;
import com.cydeo.dto.ProductDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.Product;
import com.cydeo.entity.User;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ProductRepository;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private MapperUtil mapperUtil;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public ProductDto findProductById(Long id) {
        return mapperUtil.convert(productRepository.getProductById(id).get(), new ProductDto());
    }
}


