package com.cydeo.controller;

import com.cydeo.dto.ProductDto;
import com.cydeo.entity.Product;
import com.cydeo.enums.ProductUnit;
import com.cydeo.service.CategoryService;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;



    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String listAllProducts(Model model){
        model.addAttribute("products", productService.listAllProducts());
        return "/product/product-list";
    }

    @GetMapping("/create")
    public String createProduct(Model model){

        model.addAttribute("newProduct",new Product());

        model.addAttribute("categories",categoryService.listAllCategories());

        model.addAttribute("productUnits",productService.listAllProductUnits());


        return "/product/product-create";
    }

    @PostMapping("/create")
    public String postCreatedProduct(@ModelAttribute("newProduct") ProductDto productDto){

        productService.createProduct(productDto);

        return "redirect:/products/list";
    }




}
