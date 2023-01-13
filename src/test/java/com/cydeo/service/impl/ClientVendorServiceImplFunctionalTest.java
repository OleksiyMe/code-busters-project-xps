package com.cydeo.service.impl;

import com.cydeo.dto.*;
import com.cydeo.entity.Address;
import com.cydeo.entity.ClientVendor;
import com.cydeo.entity.Company;
import com.cydeo.enums.ClientVendorType;
import com.cydeo.enums.CompanyStatus;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ClientVendorRepository;
import com.cydeo.service.CompanyService;
import com.cydeo.service.InvoiceService;
import com.cydeo.service.SecurityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientVendorServiceImplFunctionalTest {

    @Autowired
    ClientVendorRepository clientVendorRepository;
    @Autowired
    MapperUtil mapperUtil;
    @Autowired
    SecurityService securityService;
    @Autowired
    CompanyService companyService;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    ClientVendorServiceImpl clientVendorService;

    @Test
    void findClientVendorById_Test() {
//Given part
        Long id1 = 1L;
        Long id2 = 999L;
        String exceptionMessage="";
// When part
        ClientVendorDto clientVendorDto1 = clientVendorService.findClientVendorById(id1);
        try{
            ClientVendorDto clientVendorDto2 = clientVendorService.findClientVendorById(id2);
        }
            catch (Exception e){
            exceptionMessage=e.getMessage();
            }
// Then part
        Assertions.assertNotNull(clientVendorDto1);
        Assertions.assertEquals(clientVendorDto1.getId(), id1);
        Assertions.assertFalse(exceptionMessage.isBlank());
    }

    @Test
    void findById_Test() {
//Given part
        Long id1 = 1L;
        Long id2 = 999L;
        String exceptionMessage="";
// When part
        ClientVendorDto clientVendorDto1 = clientVendorService.findClientVendorById(id1);
        try{
            ClientVendorDto clientVendorDto2 = clientVendorService.findClientVendorById(id2);
        }
        catch (Exception e){
            exceptionMessage=e.getMessage();
        }
// Then part
        Assertions.assertNotNull(clientVendorDto1);
        Assertions.assertEquals(clientVendorDto1.getId(), id1);
        Assertions.assertFalse(exceptionMessage.isBlank());
    }

    @Test
    @DisplayName("testing update()")
    void update_Test() {
//Given part
        //Stubbing Behavior and return for our mocks
        when(clientVendorRepository.findById(anyLong()))
                .thenReturn(Optional.of(new ClientVendor()));
        when(securityService.getLoggedInUser())
                .thenReturn(new UserDto(1L, "Username@test.com", "Abc22#", "Abc22@", "Alex",
                        "First", "1234567890", null, new CompanyDto(1L, "CompanyName", "1234567890",
                        "http//www.vvv.com", new AddressDto(), CompanyStatus.ACTIVE),
                        true));
        when(mapperUtil.convert(any(CompanyDto.class), any(Company.class)))
                .thenReturn(new Company());
        when(mapperUtil.convert(any(ClientVendorDto.class), any(ClientVendor.class)))
                .thenReturn(new ClientVendor());
        when(clientVendorRepository.save(any(ClientVendor.class)))
                .thenReturn(new ClientVendor());
// When part
        ClientVendorDto clientVendorDto = new ClientVendorDto(1L, "Name name", "+111 (202) 555-0125",
                "http://www.vvv.com", ClientVendorType.CLIENT, new AddressDto(), new CompanyDto(1L, "CompanyName", "1234567890",
                "http//www.vvv.com", new AddressDto(), CompanyStatus.ACTIVE));
        clientVendorService.update(clientVendorDto);
// Then part
        //check the order of calling for these three mock
        InOrder inOrder = inOrder(clientVendorRepository, mapperUtil, securityService);
        //let us provide in which order calling for these two mock should be
        inOrder.verify(clientVendorRepository).findById(anyLong());
        inOrder.verify(securityService).getLoggedInUser();
        inOrder.verify(mapperUtil).convert(any(CompanyDto.class), any(Company.class));
        inOrder.verify(mapperUtil).convert(any(ClientVendorDto.class), any(ClientVendor.class));
        inOrder.verify(clientVendorRepository).save(any(ClientVendor.class));
    }

    @Test
    @DisplayName("testing listAllClientVendors()")
    void listAllClientVendors() {
//Given part
        //Stubbing Behavior and return for our mocks
        ClientVendor clientVendor1 = new ClientVendor("Name name", ClientVendorType.CLIENT, "+111 (202) 555-0125",
                "http://www.vvv.com", new Address(), new Company("CompanyName", "1234567890",
                "http//www.vvv.com", CompanyStatus.ACTIVE, new Address()));
        clientVendor1.setId(1L);
        clientVendor1.getCompany().setId(1L);
        ClientVendor clientVendor2 = new ClientVendor("Name name", ClientVendorType.CLIENT, "+111 (202) 555-0125",
                "http://www.vvv.com", new Address(), new Company("CompanyName", "1234567890",
                "http//www.vvv.com", CompanyStatus.ACTIVE, new Address()));
        clientVendor2.setId(2L);
        clientVendor2.getCompany().setId(2L);
        when(clientVendorRepository.listSortedClientVendor())
                .thenReturn(
                        Arrays.asList(clientVendor1, clientVendor2)
                );
        when(securityService.getLoggedInUser())
                .thenReturn(new UserDto(1L, "Username@test.com", "Abc22#", "Abc22@", "Alex",
                        "First", "1234567890", null, new CompanyDto(1L, "CompanyName", "1234567890",
                        "http//www.vvv.com", new AddressDto(), CompanyStatus.ACTIVE),
                        true));
        when(mapperUtil.convert(any(ClientVendor.class), any(ClientVendorDto.class)))
                .thenReturn(new ClientVendorDto());

// When part
        clientVendorService.listAllClientVendors();
// Then part
        //check the order of calling for these three mock
        InOrder inOrder = inOrder(clientVendorRepository, mapperUtil, securityService);
        //let us provide in which order calling for these two mock should be
        inOrder.verify(clientVendorRepository).listSortedClientVendor();
        inOrder.verify(securityService).getLoggedInUser();
        inOrder.verify(mapperUtil).convert(any(ClientVendor.class), any(ClientVendorDto.class));

    }


    @Test
    @DisplayName("testing updateClientVendor()")
    void updateClientVendor_Test() {
//Given part
        //Stubbing Behavior and return for our mocks
        ClientVendor clientVendor1 = new ClientVendor("Name name", ClientVendorType.CLIENT, "+111 (202) 555-0125",
                "http://www.vvv.com", new Address(), new Company("CompanyName", "1234567890",
                "http//www.vvv.com", CompanyStatus.ACTIVE, new Address()));
        clientVendor1.setId(1L);
        clientVendor1.getCompany().setId(1L);
        ClientVendor clientVendor2 = new ClientVendor("Name name", ClientVendorType.CLIENT, "+111 (202) 555-0125",
                "http://www.vvv.com", new Address(), new Company("CompanyName", "1234567890",
                "http//www.vvv.com", CompanyStatus.ACTIVE, new Address()));
        clientVendor2.setId(2L);
        clientVendor2.getCompany().setId(2L);
        when(clientVendorRepository.listSortedClientVendor())
                .thenReturn(
                        Arrays.asList(clientVendor1, clientVendor2)
                );
        when(securityService.getLoggedInUser())
                .thenReturn(new UserDto(1L, "Username@test.com", "Abc22#", "Abc22@", "Alex",
                        "First", "1234567890", null, new CompanyDto(1L, "CompanyName", "1234567890",
                        "http//www.vvv.com", new AddressDto(), CompanyStatus.ACTIVE),
                        true));
        when(mapperUtil.convert(any(ClientVendor.class), any(ClientVendorDto.class)))
                .thenReturn(new ClientVendorDto());

// When part
        clientVendorService.listAllClientVendors();
// Then part
        //check the order of calling for these three mock
        InOrder inOrder = inOrder(clientVendorRepository, mapperUtil, securityService);
        //let us provide in which order calling for these two mock should be
        inOrder.verify(clientVendorRepository).listSortedClientVendor();
        inOrder.verify(securityService).getLoggedInUser();
        inOrder.verify(mapperUtil).convert(any(ClientVendor.class), any(ClientVendorDto.class));

    }

    @Test
    @DisplayName("testing findClientVendorAddress()")
    void findClientVendorAddress_Test() {
//Given part
        //Stubbing Behavior and return for our mocks
        ClientVendor clientVendor = new ClientVendor();
        clientVendor.setAddress(new Address("1 Green Str", "", "Los Angeles", "CA", "USA",
                "12345-1234"));
        when(clientVendorRepository.findById(anyLong()))
                .thenReturn(Optional.of(clientVendor));
// When part
        Address address = clientVendorService.findClientVendorAddress(anyLong());
// Then part
        //check the order of calling for these three mock
        InOrder inOrder = inOrder(clientVendorRepository);
        //let us provide in which order calling for these two mock should be
        inOrder.verify(clientVendorRepository).findById(anyLong());
        Assertions.assertNotNull(address);
    }

    @Test
    @DisplayName("testing deleteClientVendor()")
    void deleteClientVendor_Test() {
//Given part
        //Stubbing Behavior and return for our mocks
        //Stubbing Behavior and return for our mocks
        ClientVendor clientVendor = new ClientVendor();
        clientVendor.setIsDeleted(false);
        when(clientVendorRepository.findById(anyLong()))
                .thenReturn(Optional.of(clientVendor));
// When part
        clientVendorService.deleteClientVendor(anyLong());
// Then part
        //check the order of calling for these three mock
        InOrder inOrder = inOrder(clientVendorRepository);
        //let us provide in which order calling for these two mock should be
        inOrder.verify(clientVendorRepository).findById(anyLong());
        Assertions.assertTrue(clientVendor.getIsDeleted());
    }

    @Test
    @DisplayName("testing listAllVendorTypes()")
    void listAllVendorTypes_Test() {
//Given part

// When part
        List<ClientVendorType> list = clientVendorService.listAllClientVendorTypes();
// Then part
        for (ClientVendorType clientVendorType : list) {
            Assertions.assertNotNull(clientVendorType);
        }
    }

    @Test
    @DisplayName("testing save()")
    void save_Test() {
//Given part
        //Stubbing Behavior and return for our mocks
        ClientVendorDto clientVendorDto = new ClientVendorDto(1L, "Name name", "+111 (202) 555-0125",
                "http://www.vvv.com", ClientVendorType.CLIENT, new AddressDto(), null);
        ClientVendor clientVendor = new ClientVendor("Name name", ClientVendorType.CLIENT, "+111 (202) 555-0125",
                "http://www.vvv.com", new Address(), null);
        clientVendor.setId(1L);
        UserDto loggedInUser = new UserDto(1L, "Username@test.com", "Abc22#", "Abc22@", "Alex",
                "First", "1234567890", null, new CompanyDto(1L, "CompanyName", "1234567890",
                "http//www.vvv.com", new AddressDto(), CompanyStatus.ACTIVE),
                true);
        Company company = new Company("CompanyName", "1234567890",
                "http//www.vvv.com", CompanyStatus.ACTIVE, new Address());
        when(mapperUtil.convert(any(ClientVendorDto.class), any(ClientVendor.class)))
                .thenReturn(clientVendor);
        when(securityService.getLoggedInUser())
                .thenReturn(loggedInUser);
        when(companyService.findCompanyById(anyLong()))
                .thenReturn(new CompanyDto());
        when(mapperUtil.convert(any(CompanyDto.class), any(Company.class)))
                .thenReturn(company);

// When part
        clientVendorService.save(clientVendorDto);
// Then part
        //check the order of calling for these three mock
        InOrder inOrder = inOrder(clientVendorRepository, mapperUtil, securityService, companyService);
        //let us provide in which order calling for these two mock should be
        inOrder.verify(mapperUtil).convert(any(ClientVendorDto.class), any(ClientVendor.class));
        inOrder.verify(securityService).getLoggedInUser();
        inOrder.verify(companyService).findCompanyById(anyLong());
        inOrder.verify(mapperUtil).convert(any(CompanyDto.class), any(Company.class));
        inOrder.verify(clientVendorRepository).save(any(ClientVendor.class));

        Assertions.assertEquals(clientVendor.getInsertUserId(), 1L);
        Assertions.assertEquals(clientVendor.getLastUpdateUserId(), 1L);
        Assertions.assertNotNull(clientVendor.getLastUpdateDateTime());
        Assertions.assertNotNull(clientVendor.getInsertDateTime());
        Assertions.assertEquals(clientVendor.getCompany(), company);

    }

    @Test
    @DisplayName("testing listAllVendors()")
    void listAllVendors() {
//Given part
        //Stubbing Behavior and return for our mocks
        ClientVendor clientVendor1 = new ClientVendor("Name name", ClientVendorType.VENDOR, "+111 (202) 555-0125",
                "http://www.vvv.com", new Address(), new Company("CompanyName", "1234567890",
                "http//www.vvv.com", CompanyStatus.ACTIVE, new Address()));
        clientVendor1.setId(1L);
        clientVendor1.getCompany().setId(1L);
        ClientVendor clientVendor2 = new ClientVendor("Name name", ClientVendorType.CLIENT, "+111 (202) 555-0125",
                "http://www.vvv.com", new Address(), new Company("CompanyName", "1234567890",
                "http//www.vvv.com", CompanyStatus.ACTIVE, new Address()));
        clientVendor2.setId(2L);
        clientVendor2.getCompany().setId(2L);
        when(clientVendorRepository.findAll())
                .thenReturn(
                        Arrays.asList(clientVendor1, clientVendor2)
                );
// When part
        List<ClientVendor> list = clientVendorService.listAllVendors();
// Then part
        //check the order of calling for these three mock
        InOrder inOrder = inOrder(clientVendorRepository);
        //let us provide in which order calling for these two mock should be
        inOrder.verify(clientVendorRepository).findAll();

        Assertions.assertEquals(list.size(), 1);

    }

    @Test
    @DisplayName("testing listAllClients()")
    void listAllClients() {
//Given part
        //Stubbing Behavior and return for our mocks
        ClientVendor clientVendor1 = new ClientVendor("Name name", ClientVendorType.VENDOR, "+111 (202) 555-0125",
                "http://www.vvv.com", new Address(), new Company("CompanyName", "1234567890",
                "http//www.vvv.com", CompanyStatus.ACTIVE, new Address()));
        clientVendor1.setId(1L);
        clientVendor1.getCompany().setId(1L);
        ClientVendor clientVendor2 = new ClientVendor("Name name", ClientVendorType.CLIENT, "+111 (202) 555-0125",
                "http://www.vvv.com", new Address(), new Company("CompanyName", "1234567890",
                "http//www.vvv.com", CompanyStatus.ACTIVE, new Address()));
        clientVendor2.setId(2L);
        clientVendor2.getCompany().setId(2L);
        when(clientVendorRepository.findAll())
                .thenReturn(
                        Arrays.asList(clientVendor1, clientVendor2)
                );
// When part
        List<ClientVendor> list = clientVendorService.listAllVendors();
// Then part
        //check the order of calling for these three mock
        InOrder inOrder = inOrder(clientVendorRepository);
        //let us provide in which order calling for these two mock should be
        inOrder.verify(clientVendorRepository).findAll();

        Assertions.assertEquals(list.size(), 1);

    }

    @Test
    @DisplayName("testing clientVendorCanNotBeDeleted()")
    void clientVendorCanNotBeDeleted() {

//Given Part. Preparing the mocks
        InvoiceDto invoiceDto1 = new InvoiceDto();
        invoiceDto1.setClientVendor(new ClientVendorDto());
        invoiceDto1.getClientVendor().setId(1L);
        InvoiceDto invoiceDto2 = new InvoiceDto();
        invoiceDto2.setClientVendor(new ClientVendorDto());
        invoiceDto2.getClientVendor().setId(2L);
        when(invoiceService.listAllNotDeletedInvoicesForLoggedInUser())
                .thenReturn(
                        Arrays.asList(invoiceDto1, invoiceDto2)
                );
        when(clientVendorRepository.findById(anyLong()))
                .thenReturn(Optional.of(new ClientVendor()));
        when(mapperUtil.convert(any(ClientVendor.class), any(ClientVendorDto.class)))
                .thenReturn(new ClientVendorDto());
//When part
        String result1 = clientVendorService.clientVendorCanNotBeDeleted(2L);
        String result2 = clientVendorService.clientVendorCanNotBeDeleted(5L);
// Then part
        InOrder inOrder = inOrder(invoiceService, clientVendorRepository, mapperUtil);
        //let us provide in which order calling for these two mock should be
        inOrder.verify(clientVendorRepository).findById(anyLong());
        inOrder.verify(mapperUtil).convert(any(ClientVendor.class), (any(ClientVendorDto.class)));
        inOrder.verify(invoiceService).listAllNotDeletedInvoicesForLoggedInUser();
        Assertions.assertFalse(result1.isBlank());  //must be not blank (id 2L is listed in invoice
        Assertions.assertTrue(result2.isBlank());//must be not blank (id 5L is not listed in invoice

    }


}