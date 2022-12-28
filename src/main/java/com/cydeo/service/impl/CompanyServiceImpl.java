package com.cydeo.service.impl;

import com.cydeo.dto.CompanyDto;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CompanyRepository;
import com.cydeo.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final MapperUtil mapperUtil;


    public CompanyServiceImpl(CompanyRepository companyRepository, MapperUtil mapperUtil) {
        this.companyRepository = companyRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public CompanyDto findCompanyById(Long id) {

        return mapperUtil.convert(companyRepository.findById(id), new CompanyDto());

    }

    @Override
    public void createCompany(CompanyDto company) {

    }

    @Override
    public List<CompanyDto> readAllCompanies() {
        return null;
    }

    @Override
    public CompanyDto updateCompany(Long id) {
        return null;
    }

    @Override
    public void deleteCompany(Long id) {

    }
}
