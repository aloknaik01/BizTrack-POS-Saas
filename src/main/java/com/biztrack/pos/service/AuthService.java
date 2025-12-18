package com.biztrack.pos.service;

import com.biztrack.pos.exceptions.UserException;
import com.biztrack.pos.payload.dto.UserDto;

public interface AuthService {

    AuthService signup(UserDto userDto) throws UserException;
    AuthService login(UserDto userDto);
}
