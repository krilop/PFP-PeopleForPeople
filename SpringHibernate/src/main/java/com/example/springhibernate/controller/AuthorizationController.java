package com.example.springhibernate.controller;

import com.example.springhibernate.model.Authorization;
import com.example.springhibernate.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/PFP/auth")
public class AuthorizationController {

    private final AuthService authService;

    @GetMapping("")
    public List<Authorization> getAllAuthorizations() {
        return authService.findAllAuthorizations();
    }

    @GetMapping("") // Убираем переменную пути из аннотации
    public Authorization findAuthorization(@RequestParam("loginOrEmail") String loginOrEmail, @RequestParam("pass") String password) {
        return authService.findAuthorizationByLoginOrEmail(loginOrEmail, loginOrEmail, password);
    }

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

}
