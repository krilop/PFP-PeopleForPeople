package com.example.springhibernate.service.impl;

import com.example.springhibernate.DTO.UserDTO;
import com.example.springhibernate.model.UserData;
import com.example.springhibernate.repository.UserRepository;
import com.example.springhibernate.repository.UserDataRepository;
import com.example.springhibernate.service.UDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class UserDataService implements UDService {

    private final UserDataRepository userDataRepository;
    private final UserRepository authRepository;
    @Autowired
    public UserDataService(UserDataRepository repository, UserRepository authRepository) {
        this.userDataRepository = repository;
        this.authRepository = authRepository;
    }

    @Override
    public Optional<UserData> findUserDataById(Long id) {
        Optional<UserData> userDataOptional = userDataRepository.findUserDataById(id);
        userDataOptional.get().setAge(Period.between(userDataOptional.get().getDateOfBirth(),LocalDate.now()).getYears());
        return userDataOptional;
    }
    @Override
    public List<UserData> findAllUserData()
    {
        List<UserData> userDataList = userDataRepository.findAll();
        for (UserData user:userDataList
             ) {
            user.setAge(Period.between(user.getDateOfBirth(),LocalDate.now()).getYears());
        }
        return userDataList;
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
        userData = userDataRepository.save(userData);
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @Override
    public void deleteUserDataById(Long id) {
        userDataRepository.deleteById(id);
    }

    @Override
    public void updateUserData(UserData in) {
        userDataRepository.save(in);
    }



}
