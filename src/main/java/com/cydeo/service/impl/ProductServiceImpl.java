package com.cydeo.service.impl;

import com.cydeo.dto.ProductDto;
import com.cydeo.entity.User;
import com.cydeo.entity.Product;
import com.cydeo.enums.ProductUnit;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ProductRepository;
import com.cydeo.service.ProductService;
import com.cydeo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ProductDto findProductById(Long Id) {
        return null;
    }

    @Override
    public List<ProductDto> listAllProducts() {
        User currentUser = mapperUtil.convert(securityService.getLoggedInUser(), new User());
        List<Product> productList = productRepository.findAllNotDeleted();
        return productList.stream()
                .filter(product -> product.getCategory().getCompany().getId().equals(currentUser.getCompany().getId()))
                .map(product -> mapperUtil.convert(product, new ProductDto()))
                .sorted(Comparator.comparing(ProductDto::getName))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Product product = mapperUtil.convert(productDto, new Product());


        productRepository.save(product);


        return null;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id).get();
        product.setIsDeleted(true);
        product.setName(product.getName() + "-" + product.getId());
        productRepository.save(product);
    }

    @Override
    public List<ProductUnit> listAllProductUnits() {

        List<ProductUnit> listOfProductUnits = new ArrayList<>();

        for (ProductUnit each : ProductUnit.values()) {
            listOfProductUnits.add(each);
        }

        return listOfProductUnits;
    }

    @Override
    public Boolean productListedInInvoice(Long productId) {

        //let it be stubbed for now
        if (productId==111L) return true;
        return false;
    }


}


