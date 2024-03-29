package com.example.springhibernate.service.impl;

import com.example.springhibernate.DTO.InterestDTO;
import com.example.springhibernate.model.Interest;
import com.example.springhibernate.model.UserData;
import com.example.springhibernate.repository.InterestRepository;
import com.example.springhibernate.repository.UserDataRepository;
import com.example.springhibernate.service.InterService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InterestService implements InterService {

    UserDataRepository userDataRepository;
    InterestRepository interestRepository;

    @Override
    public ResponseEntity<List<Interest>> findAllInterests()
    {
        return new ResponseEntity<List<Interest>>(interestRepository.findAll(), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Optional<Interest>> findInterestById(Long id)
    {
        return new ResponseEntity<Optional<Interest>>(interestRepository.findById(id),HttpStatus.OK);
    }

    @Transactional
    public void addUserInterest(Long userId, Long interestId) {
        // Получаем пользователя и интерес из базы данных
        UserData user = userDataRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        Interest interest = interestRepository.findById(interestId).orElseThrow(() -> new EntityNotFoundException("Интерес не найден"));

        // Добавляем интерес пользователю
        user.getInterests().add(interest);

        // Сохраняем изменения в базе данных
        userDataRepository.save(user);
    }

    @Transactional
    public void removeUserInterest(Long userId, Long interestId) {
        // Получаем пользователя из базы данных
        UserData user = userDataRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        // Ищем интерес пользователя
        Optional<Interest> interestOptional = user.getInterests().stream()
                .filter(interest -> interest.getId().equals(interestId))
                .findFirst();

        if (interestOptional.isPresent()) {
            // Если интерес найден, удаляем его из списка интересов пользователя
            user.getInterests().remove(interestOptional.get());
            userDataRepository.save(user); // Сохраняем изменения в базе данных
        } else {
            throw new EntityNotFoundException("Интерес не найден у данного пользователя");
        }
    }

    @Override
    public ResponseEntity<Interest> createNewInterest(InterestDTO in) {
        if (in.getTitleOfType() == null || in.getTitleOfType().isEmpty()) {
            return new ResponseEntity("Title of type cannot be null or empty", HttpStatus.BAD_REQUEST);
        }

        if (interestRepository.existsByTitleOfType(in.getTitleOfType())) {
            return new ResponseEntity("Interest with titleOfType " + in.getTitleOfType() + " already exists", HttpStatus.BAD_REQUEST);
        }

        Interest newInterest = new Interest();
        newInterest.setTitleOfType(in.getTitleOfType());
        newInterest.setIcon(in.getIcon());
        newInterest = interestRepository.save(newInterest);
        return new ResponseEntity<>(newInterest, HttpStatus.OK);
    }

}
