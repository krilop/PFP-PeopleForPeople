package com.example.springhibernate.service;

import com.example.springhibernate.model.Authorization;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AuthService {
    List<Authorization> findAllUsers() throws JsonProcessingException;
    void saveUser(Authorization in);
    Optional<Authorization> findUserById(Long id);

    void updateUser(Authorization in);
    void deleteUser(Long id);
}
