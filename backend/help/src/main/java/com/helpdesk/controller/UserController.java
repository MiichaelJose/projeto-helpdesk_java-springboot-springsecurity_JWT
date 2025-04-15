package com.helpdesk.controller;

import com.helpdesk.model.entity.User;
import com.helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("user")
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> create(@Validated User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            if (repository.save(user) != null) {

                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<User>> list() {
        List<User> listUsers = new ArrayList<>();

        for (User user : repository.findAll()) {
            if (!user.getRole().equals("ADMIN")) {
                listUsers.add(user);
            }
        }
        return ResponseEntity.ok().body(listUsers);
    }

    public ResponseEntity<List<User>> listUserAcess() {
        List<User> listUsers = new ArrayList<>();

        for (User user : repository.findAll()) {
            if (!user.getRole().equals("ADMIN")) {
                listUsers.add(user);
            }
        }
        return ResponseEntity.ok().body(listUsers);
    }

    /*public ResponseEntity<UserDTO> listarUser(Long id) {
        Optional<User> UserRepository = repository.findById(id);

        User User = UserRepository.get();

        return ResponseEntity.ok().body(new UserDTO(User.getId(), User.getUser(), User.getCpf()));
    }*/

    public ResponseEntity<?> delete(Long id) {
        try {
            repository.deleteById(id);

            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException a) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> update(Long id, User user) {
        try {
            User userdate = repository.findById(id).map(info -> {
                info.setName(user.getName());
                info.setPassword(passwordEncoder.encode(user.getPassword()));
                return repository.save(info);
            }).orElse(null);

            if (userdate == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> updatePermissionAcess(Long id, User user) {
        try {
            User userAcess = repository.findById(id).map(info -> {
                info.setPermission(user.getPermission());
                return repository.save(info);
            }).orElse(null);

            if (userAcess == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
