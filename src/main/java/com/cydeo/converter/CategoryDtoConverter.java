package com.cydeo.converter;

import com.cydeo.dto.CategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoConverter implements Converter<String, CategoryDto> {
    @Override
    public CategoryDto convert(String source) {
        return null;
    }
}
