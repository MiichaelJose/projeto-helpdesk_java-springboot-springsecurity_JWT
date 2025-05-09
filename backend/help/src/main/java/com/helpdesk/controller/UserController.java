package com.helpdesk.controller;

import com.helpdesk.model.dtos.UserResponseDTO;
import com.helpdesk.model.entity.User;
import com.helpdesk.repository.UserRepository;
import com.helpdesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

//    @PostMapping
//    public ResponseEntity.BodyBuilder create(@Validated @RequestBody User user) {
//        try {
//            System.out.println(user);
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//            if (repository.save(user) != null) {
//
//                return ResponseEntity.ok().build();
//            }
//            return ResponseEntity.badRequest().build();
//        } catch (DataIntegrityViolationException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(required = false) Long id) {
        return ResponseEntity.ok().body(userService.list(id));
    }

//    public ResponseEntity<?> delete(Long id) {
//        try {
//            repository.deleteById(id);
//
//            return ResponseEntity.ok().build();
//        } catch (EmptyResultDataAccessException a) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    public ResponseEntity<?> update(Long id, User user) {
//        try {
//            User userdate = repository.findById(id).map(info -> {
//                info.setName(user.getName());
////                info.setPassword(passwordEncoder.encode(user.getPassword()));
//                return repository.save(info);
//            }).orElse(null);
//
//            if (userdate == null) {
//                return ResponseEntity.notFound().build();
//            }
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
