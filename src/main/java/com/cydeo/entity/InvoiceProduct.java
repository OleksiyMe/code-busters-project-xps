package com.cydeo.entity;

import com.cydeo.entity.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice_products")
@Where(clause = "is_deleted=false")
public class InvoiceProduct extends BaseEntity {

    int quantity;
    BigDecimal price;
    int tax;
    BigDecimal profitLoss;
    int remainingQty;

    @ManyToOne   (cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

}
