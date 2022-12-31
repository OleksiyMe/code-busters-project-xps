package com.cydeo.service.impl;

import com.cydeo.dto.ProductDto;
import com.cydeo.entity.User;
import com.cydeo.entity.Product;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ProductRepository;
import com.cydeo.service.ProductService;
import com.cydeo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private MapperUtil mapperUtil;

    private final SecurityService securityService;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil, SecurityService securityService) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
    }


    @Override
    public ProductDto findProductById(Long id) {
        return mapperUtil.convert(productRepository.getProductById(id).get(), new ProductDto());
    }

    @Override
    public List<ProductDto> listAllProducts() {
        User currentUser = mapperUtil.convert(securityService.getLoggedInUser(), new User()) ;
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .filter(product -> product.getCategory().getCompany().getId().equals(currentUser.getCompany().getId()))
                .map(product -> mapperUtil.convert(product, new ProductDto()))
                .sorted(Comparator.comparing(ProductDto::getName))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}


