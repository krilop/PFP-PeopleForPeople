package com.example.springhibernate.repository;

import com.example.springhibernate.model.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Authorization, Long> {

}
