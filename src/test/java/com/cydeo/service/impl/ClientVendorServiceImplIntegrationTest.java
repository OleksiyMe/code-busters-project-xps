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
import com.cydeo.service.UserService;
import org.hibernate.SQLQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClientVendorServiceImplIntegrationTest {

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
    UserService userService;
    @Autowired
    ClientVendorServiceImpl clientVendorService;

    @Test
    void findClientVendorById_Test() {
//Given part
        Long id1 = 1L;
        Long id2 = 999L;
        String exceptionMessage = "";
// When part
        ClientVendorDto clientVendorDto1 = clientVendorService.findClientVendorById(id1);
        try {
            ClientVendorDto clientVendorDto2 = clientVendorService.findClientVendorById(id2);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
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
        String exceptionMessage = "";
// When part
        ClientVendorDto clientVendorDto1 = clientVendorService.findClientVendorById(id1);
        try {
            ClientVendorDto clientVendorDto2 = clientVendorService.findClientVendorById(id2);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
// Then part
        Assertions.assertNotNull(clientVendorDto1);
        Assertions.assertEquals(clientVendorDto1.getId(), id1);
        Assertions.assertFalse(exceptionMessage.isBlank());
    }

    @Test
    @Sql(scripts = "/testSqlScripts/createTestClientVendor.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/testSqlScripts/removeTestClientVendor.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    //  @WithMockUser(username = "admin@admin.com", authorities = { "Admin", "Manager" })
    @WithUserDetails("admin@admin.com")
    void update_Test() {
//Given part
        ClientVendorDto clientVendorDto = new ClientVendorDto(999L, "UpdatedTest", "+111 (000) 000-0000",
                "http://www.000.com", ClientVendorType.VENDOR, new AddressDto(),
                new CompanyDto(1L, "CompanyName", "1234567890",
                        "http//www.vvv.com", new AddressDto(), CompanyStatus.ACTIVE));
// When part
        clientVendorService.update(clientVendorDto);
// Then part
        //we are testing that name is changed from "Field To Update" (in .sql script) to "UpdatedTest"
        Assertions.assertEquals(clientVendorService.findClientVendorById(999L)
                .getClientVendorName(), "UpdatedTest");
        //we are testing that type is changed from "CLIENT" (in .sql script) to "VENDOR"
        Assertions.assertEquals(clientVendorService.findClientVendorById(999L)
                .getClientVendorType(), ClientVendorType.VENDOR);
    }

    @Test
    @WithUserDetails("admin@greentech.com")
    void listAllClientVendors() {
//Given part

// When part
        List<ClientVendorDto> list = clientVendorService.listAllClientVendors();
// Then part
        //we are really getting this list
        Assertions.assertNotNull(list);
        for (ClientVendorDto clientVendorDto : list) {
            //All the ClientVendors belong to loggedInUser
            Assertions.assertEquals(clientVendorDto.getCompany().getId(),
                    userService.findByUsername("admin@greentech.com").getCompany().getId());
        }

    }


    @Test
    @Sql(scripts = "/testSqlScripts/createTestClientVendor.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/testSqlScripts/removeTestClientVendor.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    //  @WithMockUser(username = "admin@admin.com", authorities = { "Admin", "Manager" })
    @WithUserDetails("admin@admin.com")
    void updateClientVendor_Test() {
//Given part
        ClientVendorDto clientVendorDto = new ClientVendorDto(999L, "UpdatedTest", "+111 (000) 000-0000",
                "http://www.000.com", ClientVendorType.VENDOR, new AddressDto(),
                new CompanyDto(1L, "CompanyName", "1234567890",
                        "http//www.vvv.com", new AddressDto(), CompanyStatus.ACTIVE));
// When part
        clientVendorService.updateClientVendor(clientVendorDto);
// Then part
        //we are testing that name is changed from "Field To Update" (in .sql script) to "UpdatedTest"
        Assertions.assertEquals(clientVendorService.findClientVendorById(999L)
                .getClientVendorName(), "UpdatedTest");
        //we are testing that type is changed from "CLIENT" (in .sql script) to "VENDOR"
        Assertions.assertEquals(clientVendorService.findClientVendorById(999L)
                .getClientVendorType(), ClientVendorType.VENDOR);
    }
    @Test
    @Sql(scripts = "/testSqlScripts/createTestClientVendor.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/testSqlScripts/removeTestClientVendor.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    //  @WithMockUser(username = "admin@admin.com", authorities = { "Admin", "Manager" })
    void findClientVendorAddress_Test() {
//Given part

// When part
        Address address = clientVendorService.findClientVendorAddress(999L);
// Then part
        Assertions.assertEquals(address.getCity(), "Test-SinCity");
    }

    @Test
    @Sql(scripts = "/testSqlScripts/createTestClientVendor.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/testSqlScripts/removeTestClientVendor.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    //  @WithMockUser(username = "admin@admin.com", authorities = { "Admin", "Manager" })
    @WithUserDetails("admin@admin.com")
    void deleteClientVendor_Test() {
//Given part

// When part
        clientVendorService.deleteClientVendor(999L);
// Then part
      ClientVendor clientVendor =clientVendorRepository.findById(999L).get();
       Assertions.assertTrue(clientVendor.getIsDeleted());
    }

    @Test
    void listAllVendorTypes_Test() {
//Given part

// When part
        List<ClientVendorType> list = clientVendorService.listAllClientVendorTypes();
// Then part
        Set<ClientVendorType> set = new HashSet<>();
        for (ClientVendorType clientVendorType : list) {
            set.add(clientVendorType);
        }
        //We have only 2 types of ClientVendor
        Assertions.assertEquals(set.size(),2);
    }

    @Test
    @Sql(scripts = "/testSqlScripts/createTestClientVendor.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/testSqlScripts/removeTestClientVendor.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithUserDetails("admin@admin.com")
    void save_Test() {
//Given part
        ClientVendorDto clientVendorDto = new ClientVendorDto(1000L, "UpdatedTest", "+111 (000) 000-0000",
                "http://www.000.com", ClientVendorType.VENDOR, new AddressDto(),
                new CompanyDto(1L, "CompanyName", "1234567890",
                        "http//www.vvv.com", new AddressDto(), CompanyStatus.ACTIVE));
// When part
        clientVendorService.save(clientVendorDto);
// Then part
        //we are testing that name is changed from "Field To Update" (in .sql script) to "UpdatedTest"
        Assertions.assertNotNull(clientVendorRepository.findById(1000L));
    }

    @Test
    void listAllVendors() {
//Given part

// When part
        List<ClientVendor> list = clientVendorService.listAllVendors();
// Then part
        for (ClientVendor clientVendor : list) {
                Assertions.assertEquals(clientVendor.getClientVendorType(),ClientVendorType.VENDOR);
        }

    }

    @Test
    void listAllClients() {
//Given part

// When part
        List<ClientVendor> list = clientVendorService.listAllClients();
// Then part
        for (ClientVendor clientVendor : list) {
            Assertions.assertEquals(clientVendor.getClientVendorType(),ClientVendorType.CLIENT);
        }

    }
    @Test
    @Sql(scripts = "/testSqlScripts/createTestClientVendor.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/testSqlScripts/removeTestClientVendor.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    //  @WithMockUser(username = "admin@admin.com", authorities = { "Admin", "Manager" })
    @WithUserDetails("admin@admin.com")
    void clientVendorCanNotBeDeleted() {

//Given Part. Preparing the mocks

//When part
        String result1 = clientVendorService.clientVendorCanNotBeDeleted(999L);
        String result2 = clientVendorService.clientVendorCanNotBeDeleted(1000L);
// Then part

        Assertions.assertFalse(result1.isBlank());  //must be not blank (id 1000L is listed in invoice
        Assertions.assertTrue(result2.isBlank());//must be not blank (id 999L is not listed in invoice

    }


}