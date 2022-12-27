package com.cydeo.converter;


import com.cydeo.dto.CompanyDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CompanyDtoConverter implements Converter<String, CompanyDto> {

    @Override
    public CompanyDto convert(String source) {
        return null;
    }
}
