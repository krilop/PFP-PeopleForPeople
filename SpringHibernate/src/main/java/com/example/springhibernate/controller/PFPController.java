package com.example.springhibernate.controller;

import com.example.springhibernate.model.Authorization;
import com.example.springhibernate.service.AuthService;
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
    @GetMapping
    public List<Authorization> findAllUsers()
    {
        return authService.findAllUsers();
    }
}
