package com.dts.intechweb.services;


import com.dts.intechweb.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUserName(String username);

    User saveUser(User user);
}
