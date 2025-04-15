package com.helpdesk.service;

import com.helpdesk.model.entity.User;
import com.helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServicePersonalizado implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetailsPersonalizado loadUserByUsername(String cpf) throws UsernameNotFoundException {
        User userRepository = repository.findByCpf(cpf);

        if (userRepository == null) {
            throw new UsernameNotFoundException("User não foi encontrado no banco");
        }

        return new UserDetailsPersonalizado(userRepository);
    }

}
