package com.jbaak.service.impl;

import com.jbaak.model.entity.User;
import com.jbaak.repository.UserRepository;
import com.jbaak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {

            throw new RuntimeException("El email ya esta registrado.");
        }

        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }
}
