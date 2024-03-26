package com.example.springhibernate.repository;

import com.example.springhibernate.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findContactsByUserDataId(Long id);
}
