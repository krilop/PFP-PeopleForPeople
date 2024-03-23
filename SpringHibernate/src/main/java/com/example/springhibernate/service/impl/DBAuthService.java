package com.example.springhibernate.service.impl;

import com.example.springhibernate.model.Authorization;
import com.example.springhibernate.repository.AuthRepository;
import com.example.springhibernate.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DBAuthService implements AuthService {

    private final AuthRepository repository;
    @Override
    public List<Authorization> findAllUsers() throws JsonProcessingException {
        return repository.findAll();
    }

    @Override
    public void saveUser(Authorization in) {
        repository.save(in);
    }

    @Override
    public Optional<Authorization> findUserById(Long id) {
       return repository.findById(id);
    }

    @Override
    public void updateUser(Authorization in) {
        repository.save(in);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
