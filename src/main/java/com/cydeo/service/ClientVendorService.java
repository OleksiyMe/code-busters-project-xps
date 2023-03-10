package com.cydeo.service;

import com.cydeo.dto.ClientVendorDto;
import com.cydeo.entity.Address;
import com.cydeo.entity.ClientVendor;
import com.cydeo.enums.ClientVendorType;

import java.time.LocalDate;
import java.util.List;

public interface ClientVendorService {

    ClientVendorDto findClientVendorById(Long id);

    ClientVendorDto findById(long id) ;//uo

    void update(ClientVendorDto clientVendorDto); //uo

    List<ClientVendorDto> listAllClientVendors();


    ClientVendorDto updateClientVendor(ClientVendorDto clientVendorDto);

    Address findClientVendorAddress(Long id);

    void deleteClientVendor(Long id);

    List<ClientVendorType> listAllClientVendorTypes();

    void save(ClientVendorDto clientVendorDto);

    List<ClientVendor> listAllVendors();

    List<ClientVendor> listAllClients();

    String clientVendorCanNotBeDeleted(Long id);

}
