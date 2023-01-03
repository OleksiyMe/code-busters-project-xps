package com.cydeo.repository;

import com.cydeo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional <Product> getProductById (Long id);

    @Query("Select p from Product p where p.isDeleted=false ORDER BY p.name")
    public List<Product> findAllNotDeleted();

}
