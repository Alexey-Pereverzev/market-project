package org.example.november_market_2.core.converters;

import org.example.november_market_2.api.UserDto;
import org.example.november_market_2.core.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDto entityToDto(User u) {
        UserDto userDto = new UserDto();
        userDto.setId(u.getId());
        userDto.setEmail(u.getEmail());
        userDto.setUsername(u.getUsername());
        return userDto;
    }
}
