package com.biztrack.pos.service;

import com.biztrack.pos.exceptions.UserException;
import com.biztrack.pos.models.User;

import java.util.List;

public interface UserService {
    User getUserFromJwtToken(String token) throws UserException;
    User getCurrentUser() throws UserException;
    User getUserByEmail(String email) throws UserException;
    User getUserById(Long id);
    List<User> getAllUser();
}
