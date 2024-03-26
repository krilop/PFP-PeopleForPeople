package com.example.springhibernate.service.impl;

import com.example.springhibernate.DTO.UserDTO;
import com.example.springhibernate.model.UserData;
import com.example.springhibernate.repository.AuthRepository;
import com.example.springhibernate.repository.UserDataRepository;
import com.example.springhibernate.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class InformationService implements InfoService {

    private final UserDataRepository repository;
    private final AuthRepository authRepository;
    @Autowired
    public InformationService(UserDataRepository repository, AuthRepository authRepository) {
        this.repository = repository;
        this.authRepository = authRepository;
    }

    @Override
    public Optional<UserData> findUserDataById(Long id) {
        Optional<UserData> userDataOptional = repository.findUserDataById(id);
        userDataOptional.get().setAge(Period.between(userDataOptional.get().getDateOfBirth(),LocalDate.now()).getYears());
        return userDataOptional;
    }

    @Override
    public ResponseEntity<UserData> createUserDataById(UserDTO in) {
        if(in.getId() == null|| !authRepository.existsById(in.getId()))
            return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
        UserData userData = new UserData();
        userData.setId(in.getId());
        userData.setName(in.getName());
        userData.setMedia(in.getMedia());
        userData.setGender(in.isGender()?1:0);
        userData.setDateOfBirth(in.getDateOfBirth());
        userData.setAge(Period.between(in.getDateOfBirth(), LocalDate.now()).getYears());
        userData.setDescription(in.getDescription());
        userData.setLastName(in.getLastName());
        userData = repository.save(userData);
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @Override
    public void deleteUserDataById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateUserData(UserData in) {
        repository.save(in);
    }



}
