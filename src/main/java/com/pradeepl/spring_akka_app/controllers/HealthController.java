package com.pradeepl.spring_akka_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
public class HealthController {
        
    // This is a simple health check endpoint
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }

    // This is a simple readiness check endpoint
    @GetMapping("/readiness")
    public ResponseEntity<String> readinessCheck() {
        return ResponseEntity.ok("READY");
    }

    // This is a simple liveness check endpoint
    @GetMapping("/liveness")
    public ResponseEntity<String> livenessCheck() {
        return ResponseEntity.ok("LIVE");
    }
}
