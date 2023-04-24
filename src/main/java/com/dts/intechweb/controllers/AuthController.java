package com.dts.intechweb.controllers;

import com.dts.intechweb.commands.UserCommand;
import com.dts.intechweb.converters.UserCommandToUser;
import com.dts.intechweb.model.User;
import com.dts.intechweb.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;
    private final UserCommandToUser converter;

    public AuthController(UserService userService, UserCommandToUser converter) {
        this.userService = userService;
        this.converter = converter;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String register(Model model) {

        model.addAttribute("chatuser", new UserCommand());
        return "auth/registration";
    }

    @PostMapping("/saveuser")
    public String saveUser(@Validated @ModelAttribute("chatuser")UserCommand user, BindingResult bindingResult)   {

        if (bindingResult.hasErrors()) {

            return "auth/registration";
        }

        userService.saveUser(converter.convert(user));

        return "redirect:/login";
    }
}
