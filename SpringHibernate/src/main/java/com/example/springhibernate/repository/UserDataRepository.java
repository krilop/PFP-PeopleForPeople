package com.example.springhibernate.repository;

import com.example.springhibernate.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    public Optional<UserData> findUserDataById(Long id);
}
