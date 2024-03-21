package com.example.springhibernate.service;

import com.example.springhibernate.model.Authorization;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthService {
    List<Authorization> findAllUsers();
    void saveUser(Authorization in);
    Authorization findUserById(Long id);

    void updateUser(Authorization in);
    void deleteUser(Long id);
}
