package com.biztrack.pos.controller;



import com.biztrack.pos.exceptions.UserException;
import com.biztrack.pos.models.User;
import com.biztrack.pos.payload.dto.UserDto;
import com.biztrack.pos.payload.response.AuthResponse;
import com.biztrack.pos.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth ")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(@RequestBody UserDto userDto)
            throws UserException
    {

        return  ResponseEntity.ok(
        (AuthResponse) authService.signup(userDto));

    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody UserDto userDto)
            throws UserException
    {

        return  ResponseEntity.ok(
                (AuthResponse) authService.login(userDto));

    }



}
