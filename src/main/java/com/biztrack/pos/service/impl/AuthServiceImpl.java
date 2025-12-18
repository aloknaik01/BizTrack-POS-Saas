package com.biztrack.pos.service.impl;
import com.biztrack.pos.configuration.JwtProvider;
import com.biztrack.pos.domain.UserRole;
import com.biztrack.pos.exceptions.UserException;
import com.biztrack.pos.mapper.UserMapper;
import com.biztrack.pos.models.User;
import com.biztrack.pos.payload.dto.UserDto;
import com.biztrack.pos.payload.response.AuthResponse;
import com.biztrack.pos.repository.UserRepository;
import com.biztrack.pos.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementaion customUserImplementaion;

    @Override
    public AuthService signup(UserDto userDto) throws UserException {
        User user = userRepository.findByEmail(userDto.getEmail());
        if(user != null){
            throw new UserException("Email id already registered");
        }

        if(userDto.getRole().equals(UserRole.ROLE_ADMIN))
        {
            throw new UserException("Role admin is not allowed");
        }

        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole(userDto.getRole());
        newUser.setFullName(userDto.getFullName());
        newUser.setPhone(userDto.getPhone());
        newUser.setLastLogin(LocalDateTime.now());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

       User savesUSer =  userRepository.save(newUser);

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());


        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully!");
        authResponse.setUser(UserMapper.toDTO(savesUSer));

        return (AuthService) authResponse;
    }

    @Override
    public AuthService login(UserDto userDto) {
        return null;
    }
}
