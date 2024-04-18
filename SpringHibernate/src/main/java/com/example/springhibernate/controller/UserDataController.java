package com.example.springhibernate.controller;

import com.example.springhibernate.DTO.UserDTO;
import com.example.springhibernate.model.UserData;
import com.example.springhibernate.service.impl.UserDataService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/PFP/")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"} ,allowCredentials = "true")
public class UserDataController {

    private final UserDataService infoService;
    @PostMapping("profile/{id}/change")
    public ResponseEntity<UserData> createUserData(@Valid @RequestBody UserDTO newUser, @PathVariable("id") Long id) {
        newUser.setId(id);
        ResponseEntity<UserData> responseEntity = infoService.createUserDataById(newUser);
        return responseEntity;
    }

    @GetMapping("profile/{id}/info")
    public ResponseEntity<UserData> findUserData(@PathVariable("id") Long id) {
        Optional<UserData> userDataOptional = infoService.findUserDataById(id);
        return userDataOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("userlist")
    public ResponseEntity<List<UserData>> findAllUserData()
    {
        return new ResponseEntity<>(infoService.findAllUserData(), HttpStatus.OK);
    }

    @PutMapping("profile/{id}/change")
    public ResponseEntity<UserData> changeUserData(@Valid @RequestBody UserDTO newUser,@PathVariable("id")Long id)
    {
        return infoService.changeUserData(newUser,id);
    }
}
