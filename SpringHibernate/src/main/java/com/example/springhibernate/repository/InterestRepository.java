package com.example.springhibernate.repository;

import com.example.springhibernate.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<Interest,Long> {
    public Boolean existsByTitleOfType(String titleOfType);
}
