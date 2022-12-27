package com.cydeo.converter;

import com.cydeo.dto.RoleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class RoleDtoConverter implements Converter<String, RoleDto> {


    @Override
    public RoleDto convert(String source) {
        return null;
    }
}
