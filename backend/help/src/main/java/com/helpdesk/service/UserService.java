package com.helpdesk.service;

import com.helpdesk.model.dtos.UserDTO;
import com.helpdesk.model.dtos.UserResponseDTO;
import com.helpdesk.model.entity.User;
import com.helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    /*@Autowired
    private PasswordEncoder passwordEncoder;*/

//    public ResponseEntity.BodyBuilder create(UserDTO user) {
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

    public ResponseEntity<?> list(Long id) {
        if (Objects.isNull(id)) {
            List<UserResponseDTO> users = userRepository.findAll()
                    .stream()
                    .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getPermission()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(users);
        }

        return userRepository.findById(id)
                .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getPermission()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
