package com.cydeo.service;

import com.cydeo.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    CompanyDto findCompanyById(Long id);

    void createCompany(CompanyDto company);
    List<CompanyDto> readAllCompanies();
    CompanyDto updateCompany(Long id);
    void deleteCompany(Long id);

}
