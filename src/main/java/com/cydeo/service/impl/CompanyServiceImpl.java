package com.cydeo.service.impl;

import com.cydeo.dto.CompanyDto;
import com.cydeo.dto.UserDto;
import com.cydeo.entity.Company;
import com.cydeo.enums.CompanyStatus;
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
                .sorted(Comparator.comparing(CompanyDto::getCompanyStatus))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) {

        Company company = companyRepository.findById(companyDto.getId()).get();

        Company convertedCompany = mapperUtil.convert(companyDto, new Company());

        convertedCompany.setId(company.getId());

        convertedCompany.setCompanyStatus(company.getCompanyStatus());

        companyRepository.save(convertedCompany);

        return findById(companyDto.getId());
    }

    @Override
    public void deleteCompany(Long id) {

    }

    @Override
    public CompanyDto findById(long id) {
        return mapperUtil.convert(companyRepository.findById(id), new CompanyDto());
    }

    @Override
    public List<CompanyDto> listAllCompaniesFilterForLoggedUser() {
        UserDto loggedInUser = securityService.getLoggedInUser();
        switch (loggedInUser.getRole().getDescription()) {
            case "Admin":
                return listAllCompanies().stream()
                        .filter(company -> company.getId().equals(loggedInUser.getCompany().getId()))
                        .collect(Collectors.toList());
            default:
                return listAllCompanies();
        }
    }

    @Override
    public void activate(Long id) {
        Company company = companyRepository.findById(id).orElseThrow();
        company.setCompanyStatus(CompanyStatus.ACTIVE);
        companyRepository.save(company);
    }

    @Override
    public void deactivate(Long id) {
        Company company = companyRepository.findById(id).orElseThrow();
        company.setCompanyStatus(CompanyStatus.PASSIVE);
        companyRepository.save(company);
    }
}
