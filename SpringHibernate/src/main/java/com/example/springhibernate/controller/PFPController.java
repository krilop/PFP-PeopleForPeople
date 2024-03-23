package com.example.springhibernate.controller;

import com.example.springhibernate.model.Authorization;
import com.example.springhibernate.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/PFP")
@AllArgsConstructor
public class PFPController {

    private final AuthService authService;
    @GetMapping("/users")
    public List<Authorization> findAllUsers() throws JsonProcessingException {
        return authService.findAllUsers();
    }
}
