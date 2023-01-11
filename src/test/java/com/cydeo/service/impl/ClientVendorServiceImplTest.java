package com.cydeo.service.impl;

import com.cydeo.dto.AddressDto;
import com.cydeo.dto.ClientVendorDto;
import com.cydeo.dto.CompanyDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.Address;
import com.cydeo.entity.ClientVendor;
import com.cydeo.entity.Company;
import com.cydeo.enums.ClientVendorType;
import com.cydeo.enums.CompanyStatus;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ClientVendorRepository;
import com.cydeo.service.SecurityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientVendorServiceImplTest {

    @Mock
    ClientVendorRepository clientVendorRepository;
    @Mock
    MapperUtil mapperUtil;
    @Mock
    SecurityService securityService;
    //    @Mock
//    CompanyService companyService;
//    @Mock
//    InvoiceService invoiceService;
//    @Mock
//    InvoiceProductService invoiceProductService;
    @InjectMocks
    ClientVendorServiceImpl clientVendorService;

    @Test
    @DisplayName("testing findClientVendorById()")
    void findClientVendorById_Test() {
//Given part
        //Stubbing Behavior and return for our mocks
        when(clientVendorRepository.findById(anyLong()))
                .thenReturn(Optional.of(new ClientVendor()));
        when(mapperUtil.convert(any(ClientVendor.class), any(ClientVendorDto.class)))
                .thenReturn(new ClientVendorDto());
// When part
        ClientVendorDto clientVendorDto = clientVendorService.findClientVendorById(anyLong());
// Then part
        //check the order of calling for these two mocks
        InOrder inOrder = inOrder(clientVendorRepository, mapperUtil);
        //let us provide in which order calling for these two mock should be

        inOrder.verify(clientVendorRepository).findById(anyLong());
        inOrder.verify(mapperUtil).convert(any(ClientVendor.class), (any(ClientVendorDto.class)));
        Assertions.assertTrue(clientVendorService.findClientVendorById(anyLong()) instanceof ClientVendorDto);
        Assertions.assertNotNull(clientVendorDto);
    }

    @Test
    @DisplayName("testing findById()")
    void findById_Test() {
//Given part
        //Stubbing Behavior and return for our mocks
        when(clientVendorRepository.findById(anyLong()))
                .thenReturn(Optional.of(new ClientVendor()));
        when(mapperUtil.convert(any(ClientVendor.class), any(ClientVendorDto.class)))
                .thenReturn(new ClientVendorDto());
// When part
        ClientVendorDto clientVendorDto = clientVendorService.findById(anyLong());
// Then part
        //check the order of calling for these two mocks
        InOrder inOrder = inOrder(clientVendorRepository, mapperUtil);
        //let us provide in which order calling for these two mock should be
        inOrder.verify(clientVendorRepository).findById(anyLong());
        inOrder.verify(mapperUtil).convert(any(ClientVendor.class), (any(ClientVendorDto.class)));
        Assertions.assertTrue(clientVendorService.findById(anyLong()) instanceof ClientVendorDto);
        Assertions.assertNotNull(clientVendorDto);
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
                "http://www.vvv.com",  new Address(), new Company("CompanyName", "1234567890",
                "http//www.vvv.com", CompanyStatus.ACTIVE, new Address()));
        clientVendor1.setId(1L);
        clientVendor1.getCompany().setId(1L);
        ClientVendor clientVendor2 = new ClientVendor("Name name", ClientVendorType.CLIENT, "+111 (202) 555-0125",
                "http://www.vvv.com",  new Address(), new Company("CompanyName", "1234567890",
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

//    @Test
//    @DisplayName("testing update()")
//    void update_Test() {
////Given part
//        //Stubbing Behavior and return for our mocks
//        when(clientVendorRepository.findById(anyLong()))
//                .thenReturn(Optional.of(new ClientVendor()));
//        when(securityService.getLoggedInUser())
//                .thenReturn(new UserDto(1L, "Username@test.com", "Abc22#", "Abc22@", "Alex",
//                        "First", "1234567890", null, new CompanyDto(1L, "CompanyName", "1234567890",
//                        "http//www.vvv.com", new AddressDto(), CompanyStatus.ACTIVE),
//                        true));
//        when(mapperUtil.convert(any(CompanyDto.class), any(Company.class)))
//                .thenReturn(new Company());
//        when(mapperUtil.convert(any(ClientVendorDto.class), any(ClientVendor.class)))
//                .thenReturn(new ClientVendor());
//        when(clientVendorRepository.save(any(ClientVendor.class)))
//                .thenReturn(new ClientVendor());
//// When part
//        ClientVendorDto clientVendorDto = new ClientVendorDto(1L, "Name name", "+111 (202) 555-0125",
//                "http://www.vvv.com", ClientVendorType.CLIENT, new AddressDto(), new CompanyDto(1L, "CompanyName", "1234567890",
//                "http//www.vvv.com", new AddressDto(), CompanyStatus.ACTIVE));
//        clientVendorService.update(clientVendorDto);
//// Then part
//        //check the order of calling for these three mock
//        InOrder inOrder = inOrder(clientVendorRepository, mapperUtil, securityService);
//        //let us provide in which order calling for these two mock should be
//        inOrder.verify(clientVendorRepository).findById(anyLong());
//        inOrder.verify(securityService).getLoggedInUser();
//        inOrder.verify(mapperUtil).convert(any(CompanyDto.class), any(Company.class));
//        inOrder.verify(mapperUtil).convert(any(ClientVendorDto.class), any(ClientVendor.class));
//
//    }

    @Test
    @DisplayName("testing updateClientVendor()")
    void updateClientVendor_Test() {
//Given part
        //Stubbing Behavior and return for our mocks
        ClientVendor clientVendor1 = new ClientVendor("Name name", ClientVendorType.CLIENT, "+111 (202) 555-0125",
                "http://www.vvv.com",  new Address(), new Company("CompanyName", "1234567890",
                "http//www.vvv.com", CompanyStatus.ACTIVE, new Address()));
        clientVendor1.setId(1L);
        clientVendor1.getCompany().setId(1L);
        ClientVendor clientVendor2 = new ClientVendor("Name name", ClientVendorType.CLIENT, "+111 (202) 555-0125",
                "http://www.vvv.com",  new Address(), new Company("CompanyName", "1234567890",
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







}