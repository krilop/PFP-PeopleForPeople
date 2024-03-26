package com.example.springhibernate.service.impl;

import com.example.springhibernate.model.Contact;
import com.example.springhibernate.repository.ContactRepository;
import com.example.springhibernate.repository.UserDataRepository;
import com.example.springhibernate.service.ContService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ContactService implements ContService {

    ContactRepository repository;
    UserDataRepository userDataRepository;

    public ResponseEntity<List<Contact>> findAllContactsById(Long id)
    {
        return new ResponseEntity<>(repository.findContactsByUserDataId(id),HttpStatus.OK);
    }

    public ResponseEntity<List<Contact>> findAllContacts(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> createContactForUser(Contact contact) {
        Contact savedContact = repository.save(contact);
        return ResponseEntity.ok(savedContact);
    }

    public ResponseEntity<?> removeContactForUser(Contact contact) {
        repository.delete(contact);
        return ResponseEntity.ok("Successfully deleted");
    }

    public Optional<Contact> findContact(Long id)
    {
        return repository.findById(id);
    }

    public ResponseEntity<?> removeContactById(Long id)
    {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
