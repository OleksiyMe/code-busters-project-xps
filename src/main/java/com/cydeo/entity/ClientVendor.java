package com.cydeo.entity;

import com.cydeo.entity.common.BaseEntity;
import com.cydeo.enums.ClientVendorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients_vendors")
@Where(clause = "is_deleted=false")
public class ClientVendor extends BaseEntity {

    private String clientVendorName;
    @Enumerated(EnumType.STRING)
    private ClientVendorType clientVendorType;
    private String phone;
    private String website;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
