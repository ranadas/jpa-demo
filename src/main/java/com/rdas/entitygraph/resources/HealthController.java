package com.rdas.entitygraph.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HealthController {
    @GetMapping("/")
    public ResponseEntity<?> getMessage() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return ResponseEntity.ok(String.format("Pinging on %s/%s/%s. Health OK \n",
                localDateTime.getYear(),
                localDateTime.getMonth(),
                localDateTime.getDayOfMonth()));
    }
}
