package com.cydeo.service.impl;

import com.cydeo.dto.CategoryDto;
import com.cydeo.dto.ClientVendorDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.Address;
import com.cydeo.entity.ClientVendor;
import com.cydeo.entity.Company;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ClientVendorRepository;
import com.cydeo.service.ClientVendorService;
import com.cydeo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientVendorServiceImpl implements ClientVendorService {

    private final ClientVendorRepository clientVendorRepository;
    private final MapperUtil mapperUtil;
    private final SecurityService securityService;

    public ClientVendorServiceImpl(ClientVendorRepository clientVendorRepository, MapperUtil mapperUtil, SecurityService securityService) {
        this.clientVendorRepository = clientVendorRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
    }

    @Override
    public ClientVendorDto findClientVendorById(Long id) {

        return mapperUtil.convert(clientVendorRepository.findById(id).get(), new ClientVendorDto());

    }

    @Override
    public List<ClientVendorDto> listAllClientVendors() {

        List<ClientVendor> clientVendorList=clientVendorRepository.listSortedClientVendor();

        UserDto loggedInUser = securityService.getLoggedInUser();

        Company currentCompany = mapperUtil.convert(
                securityService.getLoggedInUser().getCompany(), new Company());

        return clientVendorList.stream()
                .filter(clientVendor -> clientVendor.getCompany().getId().equals(loggedInUser.getCompany().getId()))
                .map(clientVendor-> mapperUtil.convert(clientVendor, new ClientVendorDto()))
                .collect(Collectors.toList());



    }

    @Override

    public ClientVendorDto updateClientVendor(ClientVendorDto clientVendorDto) {
        ClientVendor clientVendor = clientVendorRepository.findById(clientVendorDto.getId()).get();
        ClientVendor convertedClientVendor = mapperUtil.convert(clientVendorDto, new ClientVendor());

        Company currentCompany = mapperUtil.convert(
                securityService.getLoggedInUser().getCompany(), new Company());

        Address address = convertedClientVendor.getAddress();

        convertedClientVendor.setClientVendorType(clientVendor.getClientVendorType());
        convertedClientVendor.setCompany(currentCompany);
        convertedClientVendor.setId(clientVendor.getId());
        convertedClientVendor.setWebsite(clientVendor.getWebsite());
        convertedClientVendor.setAddress(address);


        clientVendorRepository.save(convertedClientVendor);

        return mapperUtil.convert(convertedClientVendor, new ClientVendorDto());
    }

    @Override
    public Address findClientVendorAddress(Long id) {
        return clientVendorRepository.findById(id).get().getAddress();

    }

    @Override
    public void deleteClientVendor(Long id) {
        ClientVendor clientVendor = clientVendorRepository.findById(id).get();
        clientVendor.setIsDeleted(true);
        clientVendorRepository.save(clientVendor);
    }


}
