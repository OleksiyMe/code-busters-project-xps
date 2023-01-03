package com.cydeo.entity;

import com.cydeo.entity.common.BaseEntity;
import com.cydeo.enums.ProductUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@Where(clause = "is_deleted=false")
public class Product extends BaseEntity {

    String name;
    int quantityInStock;
    int lowLimitAlert;

    @Enumerated(EnumType.STRING)
    ProductUnit productUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    Category category;

}
