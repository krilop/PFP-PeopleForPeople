package com.example.springhibernate.repository;

import com.example.springhibernate.model.Authorization;
import com.example.springhibernate.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthRepository extends JpaRepository<Authorization, Long> {
    Optional<UserData> findUserDataByLogin(String login);
    Optional<Authorization> findAuthorizationByLogin(String login);

    Optional<Authorization> findAuthorizationById(Long id);
}
