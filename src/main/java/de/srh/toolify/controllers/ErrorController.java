package de.srh.toolify.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/accessdenied")
    public ResponseEntity<Void> sendRedirect(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .location(URI.create("http://localhost:8081/accessdenied"))
                .build();
    }
}
