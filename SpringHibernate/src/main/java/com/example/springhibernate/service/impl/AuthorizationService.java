package com.example.springhibernate.service.impl;

import com.example.springhibernate.model.Authorization;
import com.example.springhibernate.repository.AuthRepository;
import com.example.springhibernate.service.AuthService;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class AuthorizationService implements AuthService {

    private final AuthRepository repository;

    @Autowired
    public AuthorizationService(AuthRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Authorization> findAllAuthorizations() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<Authorization> createAuthorization(Authorization in) {
        in.setHashOfPass(Hashing.sha256()
                .hashString(in.getHashOfPass(), StandardCharsets.UTF_8)
                .toString());
        Authorization tmp = repository.findAuthorizationByLoginOrEmail(in.getLogin(), in.getEmail());
        if(tmp==null) {
            repository.save(in);
            return new ResponseEntity<>(tmp,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @Override
    public void deleteAuthorizationById(Long id) {
        repository.deleteById(id);
    }


    @Override
    public void updateAuthorization(Authorization in) {
        Authorization tmp= repository.findAuthorizationByLoginOrEmail(in.getLogin(),in.getEmail());
        in.setId(tmp.getId());
        repository.save(in);
    }

    @Override
    public Authorization findAuthorizationByLoginOrEmail(String login, String email, String pass) {
        return repository.findAuthorizationByLoginOrEmailAndHashOfPass(login, email, Hashing.sha256()
                .hashString(pass, StandardCharsets.UTF_8)
                .toString());
    }
}
