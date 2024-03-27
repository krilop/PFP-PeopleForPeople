package com.example.springhibernate.service.impl;

import com.example.springhibernate.model.Authorization;
import com.example.springhibernate.model.Role;
import com.example.springhibernate.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService{

    private final AuthRepository authRepository;

    @Autowired
    public AuthorizationService(AuthRepository repository) {
        this.authRepository = repository;
    }


    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public Authorization save(Authorization user) {
        return authRepository.save(user);
    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public Authorization create(Authorization user) {
        if (authRepository.existsByLogin(user.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (authRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public Authorization getByUsername(String username) {
        return authRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public Authorization getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }


    /**
     * Выдача прав администратора текущему пользователю
     * <p>
     * Нужен для демонстрации
     */
    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
/*

    @Override
    public List<Authorization> findAllAuthorizations() {
        return authRepository.findAll();
    }

    @Override
    public ResponseEntity<Authorization> createAuthorization(Authorization in) {
        in.setHashOfPass(Hashing.sha256()
                .hashString(in.getHashOfPass(), StandardCharsets.UTF_8)
                .toString());
        Authorization tmp = authRepository.findAuthorizationByLoginOrEmail(in.getLogin(), in.getEmail());
        if(tmp==null) {
            authRepository.save(in);
            return new ResponseEntity<>(tmp,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @Override
    public void deleteAuthorizationById(Long id) {
        authRepository.deleteById(id);
    }


    @Override
    public void updateAuthorization(Authorization in) {
        Authorization tmp= authRepository.findAuthorizationByLoginOrEmail(in.getLogin(),in.getEmail());
        in.setId(tmp.getId());
        authRepository.save(in);
    }

    @Override
    public Authorization findAuthorizationByLoginOrEmail(String login, String email, String pass) {
        return authRepository.findAuthorizationByLoginOrEmailAndHashOfPass(login, email, Hashing.sha256()
                .hashString(pass, StandardCharsets.UTF_8)
                .toString());
    }*/
}
