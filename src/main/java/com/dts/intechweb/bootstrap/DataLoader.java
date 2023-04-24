package com.dts.intechweb.bootstrap;

import com.dts.intechweb.model.User;
import com.dts.intechweb.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public DataLoader(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        initialLoad();
    }
    private void initialLoad() {
        userService.saveUser(User.builder().username("admin").password(passwordEncoder.encode("123")).build());
    }

}
