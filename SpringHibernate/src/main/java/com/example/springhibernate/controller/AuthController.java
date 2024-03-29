package com.example.springhibernate.controller;

import com.example.springhibernate.DTO.JwtAuthenticationResponse;
import com.example.springhibernate.DTO.SignInRequest;
import com.example.springhibernate.DTO.SignUpRequest;
import com.example.springhibernate.service.impl.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }



   /* @GetMapping("") // Убираем переменную пути из аннотации
    public Authorization findAuthorization(@RequestParam("loginOrEmail") String loginOrEmail, @RequestParam("pass") String password) {
        return authService.findAuthorizationByLoginOrEmail(loginOrEmail, loginOrEmail, password);
    }*/
/*
    @PutMapping("/change")
    public void updateAuthorization(@RequestParam("login") String login, @RequestParam("email") String email, @RequestParam("pass") String password) {
        Authorization in = new Authorization();
        in.setEmail(email);
        in.setLogin(login);
        in.setHashOfPass(password);
        authService.updateAuthorization(in);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAuthorization(@RequestParam("id") Long id) {
        authService.deleteAuthorizationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity createAuthorization(@RequestParam("login") String login, @RequestParam("email") String email, @RequestParam("pass") String password) {
        Authorization in = new Authorization();
        in.setLogin(login);
        in.setEmail(email);
        in.setHashOfPass(password);
        in = authService.createAuthorization(in).getBody();
        return ResponseEntity.status(HttpStatus.OK).body("/registration/"+in.getId());
    }
*/
}
