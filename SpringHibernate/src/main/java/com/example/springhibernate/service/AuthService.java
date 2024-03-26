package com.example.springhibernate.service;

import com.example.springhibernate.model.Authorization;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthService {
    List<Authorization> findAllAuthorizations();

    ResponseEntity<Authorization> createAuthorization(Authorization in);

    void deleteAuthorizationById(Long id);

    void updateAuthorization(Authorization in);

    Authorization findAuthorizationByLoginOrEmail(String login, String email, String pass);
}
