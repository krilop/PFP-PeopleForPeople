package com.example.springhibernate.controller;

import com.example.springhibernate.DTO.InterestDTO;
import com.example.springhibernate.model.Interest;
import com.example.springhibernate.service.impl.InterestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/PFP/interests")
@CrossOrigin(origins = "http://localhost:4200")
public class InterestController {

    private final InterestService interestService;


    @GetMapping("/{id}")
    public ResponseEntity<List<Interest>> findAllInterest()
    {
        return interestService.findAllInterests();
    }

    @PostMapping("/{userId}/{interestId}")
    public ResponseEntity<?> addUserInterest(@PathVariable("userId") Long userId, @PathVariable("interestId") Long interestId) {
        interestService.addUserInterest(userId, interestId );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/{interestId}")
    public ResponseEntity<?> deleteUserInterest(@PathVariable("userId") Long userId, @PathVariable("interestId") Long interestId) {
        interestService.removeUserInterest(userId, interestId );
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> createNewInterest(@Valid @RequestBody InterestDTO newInterest)
    {
        return  interestService.createNewInterest(newInterest);
    }

}
