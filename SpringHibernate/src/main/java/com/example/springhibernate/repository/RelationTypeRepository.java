package com.example.springhibernate.repository;

import com.example.springhibernate.model.RelationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationTypeRepository extends JpaRepository<RelationType, Long> {
}
