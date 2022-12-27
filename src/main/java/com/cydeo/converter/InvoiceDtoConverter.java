package com.cydeo.converter;

import com.cydeo.dto.InvoiceDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDtoConverter implements Converter<String, InvoiceDto> {
    @Override
    public InvoiceDto convert(String source) {
        return null;
    }
}
