package com.example.springhibernate.repository;

import com.example.springhibernate.model.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Authorization, Long> {
    Optional<Authorization> findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
}
