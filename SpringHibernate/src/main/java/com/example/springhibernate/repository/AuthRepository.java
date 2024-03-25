package com.example.springhibernate.repository;

import com.example.springhibernate.model.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Authorization, Long> {
    Authorization findAuthorizationByLoginOrEmailAndHashOfPass(String login, String email, String hashOfPass);
    Authorization findAuthorizationByLoginOrEmail(String login, String email);
}
