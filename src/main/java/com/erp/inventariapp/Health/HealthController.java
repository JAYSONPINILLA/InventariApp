package com.erp.inventariapp.Health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // si todos tus endpoints están bajo /api
public class HealthController {
    @GetMapping("/health")
    public String health() {
        return "*** Jayson Health is OK ***";
    }
}
