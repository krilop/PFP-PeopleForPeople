package com.example.springhibernate.controller;

import com.example.springhibernate.DTO.ContactDTO;
import com.example.springhibernate.model.Contact;
import com.example.springhibernate.model.ContactType;
import com.example.springhibernate.model.UserData;
import com.example.springhibernate.service.impl.ContactService;
import com.example.springhibernate.service.impl.UserDataService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/PFP/")
public class ContactController {

    private final UserDataService userDataService;
    private final ContactService contactService;

    @GetMapping("/profile/{id}/contacts")
    public ResponseEntity<?> findAllContactsById(@PathVariable(name = "id") Long id)
    {
        return contactService.findAllContactsById(id);
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<?> findAllContacts()
    {
        return contactService.findAllContacts();
    }

    @PostMapping("/contacts/{userId}")
    public ResponseEntity<?> addContactToUser(@PathVariable("userId") Long userId, @Valid @RequestBody ContactDTO contactDTO) {
        Optional<UserData> userOptional = userDataService.findUserDataById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // или другой подходящий ответ
        }
        UserData user = userOptional.get();

        Contact contact = new Contact();
        contact.setInfo(contactDTO.getInfo());
        contact.setContactType(ContactType.valueOf(contactDTO.getContactType().toUpperCase()));
        contact.setUserData(user);

        try {
            contactService.createContactForUser(contact);
            user.getContacts().add(contact); // Добавляем контакт к списку контактов пользователя
            userDataService.updateUserData(user); // Сохраняем обновленные данные пользователя
            return ResponseEntity.ok().build(); // или другой подходящий ответ
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // или другой подходящий ответ
        }
    }


    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<?> deleteContact(@PathVariable("contactId") Long contactId) {
        Optional<Contact> contactOptional = contactService.findContact(contactId);
        if (contactOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        contactService.removeContactById(contactId);
        return ResponseEntity.noContent().build();
    }
}
