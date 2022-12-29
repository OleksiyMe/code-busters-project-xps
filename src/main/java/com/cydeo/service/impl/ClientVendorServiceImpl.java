package com.cydeo.service.impl;

import com.cydeo.dto.CategoryDto;
import com.cydeo.dto.ClientVendorDto;
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

        List<ClientVendor> clientVendorList=clientVendorRepository.findAll();

        Company currentCompany = mapperUtil.convert(
                securityService.getLoggedInUser().getCompany(), new Company());

        return clientVendorList.stream()
                .filter(clientVendor -> clientVendor.getCompany().getId().equals(currentCompany.getId()))
                .map(clientVendor-> mapperUtil.convert(clientVendor, new ClientVendorDto()))
                .sorted(Comparator.comparing(ClientVendorDto::getClientVendorType))
                .sorted(Comparator.comparing(ClientVendorDto::getClientVendorName))
                .collect(Collectors.toList());



    }


}
