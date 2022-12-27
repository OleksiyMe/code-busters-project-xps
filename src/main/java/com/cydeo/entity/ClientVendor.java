package com.cydeo.entity;

import com.cydeo.entity.common.BaseEntity;
import com.cydeo.enums.ClientVendorType;

import javax.persistence.*;

@Entity
@Table(name = "clients_vendors")
public class ClientVendor extends BaseEntity {

    private String clientVendorName;
    @Enumerated(EnumType.STRING)
    private ClientVendorType clientVendorType;
    private String phone;
    private String website;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
