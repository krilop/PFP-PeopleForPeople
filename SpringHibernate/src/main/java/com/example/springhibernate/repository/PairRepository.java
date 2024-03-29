package com.example.springhibernate.repository;

import com.example.springhibernate.model.Pair;
import com.example.springhibernate.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PairRepository extends JpaRepository<Pair,Long> {
    public int countByUserId_IdAndAnotherId_Id(Long userId, Long anotherId);

    public Pair findByUserId_IdAndAnotherId_Id(Long userId, Long anotherId);

    public List<Pair> findPairsByUserId(UserData userId);


}
