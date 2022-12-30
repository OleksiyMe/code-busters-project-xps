package com.cydeo.service;

import com.cydeo.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    CompanyDto findCompanyById(Long id);

    CompanyDto getCompanyDto();

    void createCompany(CompanyDto company);
    List<CompanyDto> listAllCompanies();
    CompanyDto updateCompany(CompanyDto companyDto);
    void deleteCompany(Long id);

    CompanyDto findById(long id);

    List<CompanyDto> listAllCompaniesFilterForLoggedUser();
    void activate(Long id);
    void deactivate(Long id);
}
