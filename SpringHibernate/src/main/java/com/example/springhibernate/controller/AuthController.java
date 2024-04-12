package com.example.springhibernate.controller;

import com.example.springhibernate.DTO.JwtAuthenticationResponse;
import com.example.springhibernate.DTO.SignInRequest;
import com.example.springhibernate.DTO.SignUpRequest;
import com.example.springhibernate.service.impl.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "true")
public class AuthController {

    private final AuthenticationService authenticationService;
    private static final Logger logger = LogManager.getLogger(AuthController.class);
    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        JwtAuthenticationResponse tmp = authenticationService.signUp(request);
        logger.info(tmp);
        return tmp;
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        JwtAuthenticationResponse tmp = authenticationService.signIn(request);
        logger.info(tmp);
        return tmp;
    }

}
