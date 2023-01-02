package com.cydeo.service.impl;

import com.cydeo.dto.ClientVendorDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.Address;
import com.cydeo.entity.ClientVendor;
import com.cydeo.entity.Company;
import com.cydeo.enums.ClientVendorType;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ClientVendorRepository;
import com.cydeo.service.ClientVendorService;
import com.cydeo.service.CompanyService;
import com.cydeo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientVendorServiceImpl implements ClientVendorService {

    private final ClientVendorRepository clientVendorRepository;
    private final MapperUtil mapperUtil;
    private final SecurityService securityService;
    private final CompanyService companyService;

    public ClientVendorServiceImpl(ClientVendorRepository clientVendorRepository, MapperUtil mapperUtil, SecurityService securityService, CompanyService companyService) {
        this.clientVendorRepository = clientVendorRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
        this.companyService = companyService;
    }

    @Override
    public ClientVendorDto findClientVendorById(Long id) {

        return mapperUtil.convert(clientVendorRepository.findById(id).get(), new ClientVendorDto());

    }

    @Override
    public List<ClientVendorDto> listAllClientVendors() {

        List<ClientVendor> clientVendorList = clientVendorRepository.listSortedClientVendor();

        UserDto loggedInUser = securityService.getLoggedInUser();

        Company currentCompany = mapperUtil.convert(
                securityService.getLoggedInUser().getCompany(), new Company());

        return clientVendorList.stream()
                .filter(clientVendor -> clientVendor.getCompany().getId().equals(loggedInUser.getCompany().getId()))
                .map(clientVendor -> mapperUtil.convert(clientVendor, new ClientVendorDto()))
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

    @Override
    public List<ClientVendorType> listAllClientVendorTypes() {

        List<ClientVendorType> types = new ArrayList<>();

        for (ClientVendorType each : ClientVendorType.values()) {
            types.add(each);
        }
        return types;
    }

    @Override
    public void save(ClientVendorDto clientVendorDto) {
        ClientVendor clientVendor = mapperUtil.convert(clientVendorDto, new ClientVendor());
        UserDto loggedInUser = securityService.getLoggedInUser();
        clientVendor.setCompany(mapperUtil.convert(
                companyService.findCompanyById(loggedInUser.getCompany().getId()), new Company()
        ));
        clientVendor.setInsertUserId(loggedInUser.getId());
        clientVendor.setLastUpdateUserId(loggedInUser.getId());
        clientVendor.setLastUpdateDateTime(LocalDateTime.now());
        clientVendor.setInsertDateTime(LocalDateTime.now());

        clientVendorRepository.save(clientVendor);
    }

}
