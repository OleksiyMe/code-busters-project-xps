package com.cydeo.service.impl;

import com.cydeo.dto.ProductDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.User;
import com.cydeo.entity.Product;
import com.cydeo.enums.ProductUnit;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ProductRepository;
import com.cydeo.service.InvoiceProductService;
import com.cydeo.service.ProductService;
import com.cydeo.service.SecurityService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private MapperUtil mapperUtil;

    private final SecurityService securityService;
    private final InvoiceProductService invoiceProductService;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil,
                              SecurityService securityService, @Lazy InvoiceProductService invoiceProductService) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
        this.invoiceProductService = invoiceProductService;
    }


    @Override
    public ProductDto findProductById(Long id) {
        return listAllProducts().stream()
                .filter(productDto -> productDto.getId().equals(id))
                .findFirst().orElseThrow(() -> new NoSuchElementException("No product with id " + id));
    }

    @Override
    public List<ProductDto> listAllProducts() {
        User currentUser = mapperUtil.convert(securityService.getLoggedInUser(), new User());
        List<Product> productList = productRepository.findAllNotDeleted();
        return productList.stream()
                .filter(product -> product.getCategory().getCompany().getId().equals(currentUser.getCompany().getId()))
                .map(product -> mapperUtil.convert(product, new ProductDto()))
                .sorted(Comparator.comparing(productDto -> productDto.getCategory().getDescription()))
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

        Product product = productRepository.save(mapperUtil.convert(productDto, new Product()));
        return mapperUtil.convert(product, new ProductDto());
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
        return invoiceProductService.findAllNotDeleted().stream()
                .anyMatch(invoiceDto -> invoiceDto.getProduct().getId().equals(productId));
    }

    @Override
    public void save(ProductDto productDto) {
        productRepository.save(mapperUtil.convert(productDto, new Product()));
    }

    @Override
    public Boolean productNameExists(ProductDto productDtoToSave) {

        return listAllProducts().stream()
                .anyMatch(productDto -> productDto.getName().equals(productDtoToSave.getName()));

    }


}


