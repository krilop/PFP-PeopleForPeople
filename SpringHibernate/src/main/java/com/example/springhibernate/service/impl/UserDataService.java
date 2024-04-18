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
        userData.setAuthorization(authRepository.findById(in.getId()).get());
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


    public ResponseEntity<UserData> changeUserData(UserDTO in, Long id) {
        if (in.getId() == null || !authRepository.existsById(in.getId())) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        try{
            Optional<UserData> userData = findUserDataById(id);
            UserData user = userData.get();
            user.setId(id);
            user.setName(in.getName().isBlank() ? user.getName() : in.getName());
            user.setMedia(in.getMedia().isBlank() ? user.getMedia() : in.getMedia());
            user.setGender(in.isGender() ? 1 : 0);
            user.setDateOfBirth(in.getDateOfBirth() != null ? in.getDateOfBirth() : user.getDateOfBirth());
            user.setDescription(in.getDescription().isBlank() ? user.getDescription() : in.getDescription());
            user.setLastName(in.getLastName().isBlank() ? user.getLastName() : in.getLastName());
            user.setAuthorization(authRepository.findById(in.getId()).get());
            user = userDataRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
