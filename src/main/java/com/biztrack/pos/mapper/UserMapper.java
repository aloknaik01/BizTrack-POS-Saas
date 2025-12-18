package com.biztrack.pos.mapper;


import com.biztrack.pos.models.User;
import com.biztrack.pos.payload.dto.UserDto;
import java.time.LocalDateTime;

public class UserMapper {

    public static UserDto toDTO(User savedUSer)
    {
UserDto userDto = new UserDto();
userDto.setId(savedUSer.getId());
userDto.setEmail(savedUSer.getEmail());
userDto.setRole(savedUSer.getRole());
        userDto.setCreatedAt(LocalDateTime.now());
                userDto.setLastLogin(LocalDateTime.now());
                userDto.setUpdatedAt(LocalDateTime.now());
        userDto.setFullName(savedUSer.getFullName());
        userDto.setPhone(savedUSer.getPhone());
return  userDto;
    }
}
