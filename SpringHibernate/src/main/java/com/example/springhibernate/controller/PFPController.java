package com.example.springhibernate.controller;

import com.example.springhibernate.DTO.ContactDTO;
import com.example.springhibernate.DTO.InterestDTO;
import com.example.springhibernate.DTO.UserDTO;
import com.example.springhibernate.model.Authorization;
import com.example.springhibernate.model.Interest;
import com.example.springhibernate.model.UserData;
import com.example.springhibernate.service.AuthService;
import com.example.springhibernate.service.impl.ContactService;
import com.example.springhibernate.service.impl.InformationService;
import com.example.springhibernate.service.impl.InterestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/PFP")
public class PFPController {

    private final AuthService authService;
    private final InformationService infoService;
    private final InterestService interestService;
    private final ContactService contactService;
    @GetMapping("/authorizations")
    public List<Authorization> getAllAuthorizations() {
        return authService.findAllAuthorizations();
    }

    @GetMapping("/auth") // Убираем переменную пути из аннотации
    public Authorization findAuthorization(@RequestParam("loginOrEmail") String loginOrEmail, @RequestParam("pass") String password) {
        return authService.findAuthorizationByLoginOrEmail(loginOrEmail, loginOrEmail, password);
    }

    @PutMapping("/auth/change")
    public void updateAuthorization(@RequestParam("login") String login, @RequestParam("email") String email, @RequestParam("pass") String password) {
        Authorization in = new Authorization();
        in.setEmail(email);
        in.setLogin(login);
        in.setHashOfPass(password);
        authService.updateAuthorization(in);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAuthorization(@RequestParam("id") Long id) {
        authService.deleteAuthorizationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity createAuthorization(@RequestParam("login") String login, @RequestParam("email") String email, @RequestParam("pass") String password) {
        Authorization in = new Authorization();
        in.setLogin(login);
        in.setEmail(email);
        in.setHashOfPass(password);
        in = authService.createAuthorization(in).getBody();
        return ResponseEntity.status(HttpStatus.OK).body("/registration/"+in.getId());
    }

    @GetMapping
    @PostMapping("/registration/{id}")
    public ResponseEntity<String> createUserData(@Valid @RequestBody UserDTO newUser, @PathVariable("id") Long id) {
        newUser.setId(id);
        ResponseEntity<UserData> responseEntity = infoService.createUserDataById(newUser);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            UserData userData = responseEntity.getBody();
            Long userId = userData.getId();
            String redirectUrl = "/profile/" + userId;
            return ResponseEntity.status(statusCode).body(redirectUrl);
        } else {
            return ResponseEntity.status(statusCode).build();
        }
    }

    @GetMapping("/profile/{id}/info")
    public ResponseEntity<UserData> findUserData(@PathVariable("id") Long id) {
        Optional<UserData> userDataOptional = infoService.findUserDataById(id);
        return userDataOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/interests/{id}")
    public ResponseEntity<List<Interest>> findAllInterest()
    {
        return interestService.findAllInterests();
    }

    @PostMapping("/interests/{userId}/{interestId}")
    public ResponseEntity<?> addUserInterest(@PathVariable("userId") Long userId, @PathVariable("interestId") Long interestId) {
            interestService.addUserInterest(userId, interestId );
            return ResponseEntity.ok().build();
    }

    @DeleteMapping("/interests/{userId}/{interestId}")
    public ResponseEntity<?> deleteUserInterest(@PathVariable("userId") Long userId, @PathVariable("interestId") Long interestId) {
        interestService.removeUserInterest(userId, interestId );
        return ResponseEntity.ok().build();
    }

    @PostMapping("/interests/addnew")
    public ResponseEntity<?> createNewInterest(@Valid @RequestBody InterestDTO newInterest)
    {
        return  interestService.createNewInterest(newInterest);
    }

    @GetMapping("/profile/{id}/contacts")
    public ResponseEntity<?> findAllContactsById(@PathVariable(name = "id") Long id)
    {
        return contactService.findAllContactsById(id);
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<?> createContact()
    {
        return contactService.findAllContacts();
    }

    @PostMapping("/profile/{id}/addNewContact")
    public ResponseEntity<?> addNewContactForUser(@Valid @RequestBody ContactDTO)
    {
        return null;
    }
}
