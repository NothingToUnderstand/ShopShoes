package com.viktor.task.shoes.manager.web.controllers;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("")
public class LoginController {

    @GetMapping(path = "/sbgerb")
    public String login() {
        return new String("You are authenticated");
    }
}