package com.cydeo.converter;

import com.cydeo.dto.ClientVendorDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientVendorDtoConverter implements Converter<String, ClientVendorDto>
{
    @Override
    public ClientVendorDto convert(String source) {
        return null;
    }
}
