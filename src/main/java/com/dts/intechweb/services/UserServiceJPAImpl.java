package com.dts.intechweb.services;

import com.dts.intechweb.model.User;
import com.dts.intechweb.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceJPAImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceJPAImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
