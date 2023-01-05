package com.cydeo.controller;

import com.cydeo.dto.ProductDto;
import com.cydeo.entity.Product;
import com.cydeo.service.CategoryService;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.util.NoSuchElementException;

import javax.validation.Valid;

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
    public String listAllProducts(Model model) {
        model.addAttribute("products", productService.listAllProducts());
        return "/product/product-list";
    }

    @GetMapping("/create")
    public String createProduct(Model model) {

        model.addAttribute("newProduct", new Product());

        model.addAttribute("categories", categoryService.listAllCategories());

        model.addAttribute("productUnits", productService.listAllProductUnits());


        return "/product/product-create";
    }

    @PostMapping("/create")
    public String postCreatedProduct(@Valid @ModelAttribute("newProduct") ProductDto productDto, BindingResult bindingResult, Model model) {
        Boolean productNameExists = productService.productNameExists(productDto);

        if (bindingResult.hasErrors() || productNameExists) {
            if (productNameExists) {
                bindingResult.rejectValue("name", " ", "A product with this name already exists. Please try different name.");

            }
            model.addAttribute("categories", categoryService.listAllCategories());
            model.addAttribute("productUnits", productService.listAllProductUnits());
            return "/product/product-create";
        }

        productService.createProduct(productDto);

        return "redirect:/products/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId, Model model) {

        if (productService.productListedInInvoice(productId)) {
            model.addAttribute("products", productService.listAllProducts());
            model.addAttribute("errorMessage",
                    "!!ERROR!!: Product with id " + productId +
                            " is listed in invoice! You can not delete it");
            return "/product/product-list";
        }
        productService.deleteProductById(productId);

        return "redirect:/products/list";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model) {

        model.addAttribute("product", productService.findProductById(id));
        model.addAttribute("categories", categoryService.listAllCategories());
        model.addAttribute("productUnits", productService.listAllProductUnits());

        return "product/product-update";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@ModelAttribute("newProduct") ProductDto productDto) {

        productService.updateProduct(productDto);

        return "redirect:/products/list";

    }
}
