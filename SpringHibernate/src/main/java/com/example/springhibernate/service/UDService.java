package com.example.springhibernate.service;

import com.example.springhibernate.DTO.UserDTO;
import com.example.springhibernate.model.UserData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UDService {

    Optional<UserData> findUserDataById(Long id);

    List<UserData> findAllUserData();

    default ResponseEntity<UserData> createUserDataById(UserDTO in) {
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    void deleteUserDataById(Long id);

    void updateUserData(UserData in);
}
