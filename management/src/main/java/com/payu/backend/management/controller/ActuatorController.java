package com.payu.backend.management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuator")
public class ActuatorController {

    @GetMapping(value = "/health")
    public String health() {
        return "OK";
    }
}
