package com.cydeo.converter;

import com.cydeo.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter implements Converter<String, UserDto> {


    @Override
    public UserDto convert(String source) {
        return null;
    }
}
