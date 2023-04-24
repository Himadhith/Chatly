package com.dts.intechweb.converters;

import com.dts.intechweb.commands.UserCommand;
import com.dts.intechweb.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserCommandToUser {

    private final PasswordEncoder passwordEncoder;

    public UserCommandToUser(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User convert(UserCommand source) {
        if (source == null) {
            return null;
        }
        return User.builder().username(source.getUsername()).password(passwordEncoder.encode(source.getPassword())).build();
    }

}
