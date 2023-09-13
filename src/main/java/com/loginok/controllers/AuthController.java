package com.loginok.controllers;

import com.loginok.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public String login()
    {
        return "Login OK!";
    }

    @PostMapping(value = "register")
    public String register()
    {
        return "Register Ok!";
    }
}
