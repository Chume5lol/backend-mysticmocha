package com.mysticmocha.mysticmocha.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/", "/home" })
public class HomeController {
    public String home() {
        return " home";
    }
}
