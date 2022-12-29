package com.cydeo.service.impl;

import com.cydeo.dto.CompanyDto;
import com.cydeo.entity.Company;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CompanyRepository;
import com.cydeo.service.CompanyService;
import com.cydeo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final MapperUtil mapperUtil;

    private final SecurityService securityService;


    public CompanyServiceImpl(CompanyRepository companyRepository, MapperUtil mapperUtil, SecurityService securityService) {
        this.companyRepository = companyRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
    }

    @Override
    public CompanyDto findCompanyById(Long id) {

        return mapperUtil.convert(companyRepository.findById(id), new CompanyDto());

    }

    @Override
    public CompanyDto getCompanyDto() {
        return securityService.getLoggedInUser().getCompany();
    }

    @Override
    public void createCompany(CompanyDto company) {

    }

    @Override
    public List<CompanyDto> listAllCompanies() {
        List<Company> companyList = companyRepository.findAll();
        return companyList.stream().filter(company -> company.getId() != 1).map(company ->
                mapperUtil.convert(company, new CompanyDto()))
                .sorted( Comparator.comparing(CompanyDto::getCompanyStatus))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto updateCompany(Long id) {
        return null;
    }

    @Override
    public void deleteCompany(Long id) {

    }

    @Override
    public CompanyDto findById(long id) {
        return mapperUtil.convert(companyRepository.findById(id), new CompanyDto());
    }
}
