package com.cydeo.converter;

import com.cydeo.dto.ProductDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoConverter implements Converter <String, ProductDto>{


    @Override
    public ProductDto convert(String source) {
        return null;
    }
}
