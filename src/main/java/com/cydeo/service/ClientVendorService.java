package com.cydeo.service;

import com.cydeo.dto.ClientVendorDto;
import com.cydeo.entity.Address;
import com.cydeo.enums.ClientVendorType;

import java.util.List;

public interface ClientVendorService {

    ClientVendorDto findClientVendorById(Long id);

    List<ClientVendorDto> listAllClientVendors();


    ClientVendorDto updateClientVendor(ClientVendorDto clientVendorDto);

    Address findClientVendorAddress(Long id);

    void deleteClientVendor(Long id);

    List<ClientVendorType> listAllClientVendorTypes();

}
