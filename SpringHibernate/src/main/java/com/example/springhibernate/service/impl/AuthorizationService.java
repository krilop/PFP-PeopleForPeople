package com.example.springhibernate.service.impl;

import com.example.springhibernate.model.Authorization;
import com.example.springhibernate.repository.AuthRepository;
import com.example.springhibernate.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class AuthorizationService implements AuthService {

    private final AuthRepository repository;
    @Autowired
    public AuthorizationService(AuthRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Authorization> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public void saveUser(Authorization in) {
        repository.save(in);
    }

    @Override
    public Optional<Authorization> findUserById(Long id) {
        return repository.findAuthorizationById(id);
    }

    @Override
    public void updateUser(Authorization in) {
        repository.save(in);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
