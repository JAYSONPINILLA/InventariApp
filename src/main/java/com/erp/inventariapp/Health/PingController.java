package com.erp.inventariapp.Health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    
    @GetMapping("/")
    public String hello() {
        return "*** Jayson Backend is running ***";
    }
}