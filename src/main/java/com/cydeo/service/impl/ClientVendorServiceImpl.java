package com.cydeo.service.impl;

import com.cydeo.dto.ClientVendorDto;
import com.cydeo.dto.CompanyDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.Address;
import com.cydeo.entity.ClientVendor;
import com.cydeo.entity.Company;
import com.cydeo.enums.ClientVendorType;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ClientVendorRepository;
import com.cydeo.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientVendorServiceImpl implements ClientVendorService {

    private final ClientVendorRepository clientVendorRepository;
    private final MapperUtil mapperUtil;
    private final SecurityService securityService;
    private final CompanyService companyService;
    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;

    public ClientVendorServiceImpl(ClientVendorRepository clientVendorRepository, MapperUtil mapperUtil, SecurityService securityService, CompanyService companyService, InvoiceService invoiceService, InvoiceProductService invoiceProductService) {
        this.clientVendorRepository = clientVendorRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
        this.companyService = companyService;
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
    }

    @Override
    public ClientVendorDto findClientVendorById(Long id) {

        return mapperUtil.convert(clientVendorRepository.findById(id).get(), new ClientVendorDto());

    }
    //---------------------------------------------------------------------------------uo
    @Override
    public ClientVendorDto findById(long id) {
        ClientVendor clientVendor = clientVendorRepository.findById(id).get();
        return mapperUtil.convert(clientVendor, new ClientVendorDto());
    }

    @Override
    public void update(ClientVendorDto clientVendorDto) {
        ClientVendor clientVendor = clientVendorRepository.findById(clientVendorDto.getId()).get();
        clientVendor.setClientVendorType(clientVendorDto.getClientVendorType());

        UserDto loggedInUser = securityService.getLoggedInUser();

        CompanyDto companyDto = loggedInUser.getCompany();
        Company company = mapperUtil.convert(companyDto, new Company());

        ClientVendor convert = mapperUtil.convert(clientVendorDto, new ClientVendor());
        convert.setId(clientVendorDto.getId());
        convert.setCompany(company);

        clientVendorRepository.save(convert);
    }

    //---------------------------------------------------------------------------------uo
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

    @Override
    public List<ClientVendor> listAllVendors() {
        return clientVendorRepository.findAll().stream()
                .filter(clientVendor -> clientVendor.getClientVendorType().getValue().equals("Vendor"))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientVendor> listAllClients() {
        return clientVendorRepository.findAll().stream()
                .filter(clientVendor -> clientVendor.getClientVendorType().getValue().equals("Client"))
                .collect(Collectors.toList());
    }

    @Override
    public String clientVendorCanNotBeDeleted(Long id) {
        ClientVendorDto clientVendorDto = findClientVendorById(id);
        if (invoiceService.listAllNotDeletedInvoicesForLoggedInUser().stream()
                .anyMatch(invoiceDto -> invoiceDto.getClientVendor().getId().equals(id)))
            return "!!ERROR!!: You can not delete ClientVendor " + clientVendorDto.getClientVendorName() +
                    ". It is listed in invoice(invoices).";
//return not empty string -- we can not delete ClientVendor
//return empty string -- we can delete ClientVendor
        return "";
    }

}
