package com.example.springhibernate.service;

import com.example.springhibernate.DTO.InterestDTO;
import com.example.springhibernate.model.Interest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface InterService{

    ResponseEntity<List<Interest>> findAllInterests();

    ResponseEntity<Optional<Interest>> findInterestById(Long id);

    ResponseEntity<Interest> createNewInterest(InterestDTO in);
}
