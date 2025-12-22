package com.biztrack.pos.service;

import com.biztrack.pos.exceptions.UserException;
import com.biztrack.pos.models.User;

import java.util.List;

public interface UserService {
    User getUserFromJwtToken(String token) throws UserException;
    User getCurrentUser();
    User getUserByEmail(String email);
    User getUserById(Long id);
    List<User> getAllUser();
}
