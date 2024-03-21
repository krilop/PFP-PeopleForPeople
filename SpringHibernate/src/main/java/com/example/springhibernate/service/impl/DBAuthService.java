package com.example.springhibernate.service.impl;

import com.example.springhibernate.model.Authorization;
import com.example.springhibernate.repository.AuthDAO;
import com.example.springhibernate.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DBAuthService implements AuthService {

    private final AuthDAO repository;
    @Override
    public List<Authorization> findAllUsers() {
        return repository.findAllUsers();
    }

    @Override
    public void saveUser(Authorization in) {
        repository.saveUser(in);
    }

    @Override
    public Authorization findUserById(Long id) {
        return repository.findUserById(id);
    }

    @Override
    public void updateUser(Authorization in) {
        repository.updateUser(in);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        repository.deleteUser(id);
    }
}
