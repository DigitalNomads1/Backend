package com.dosi.controllers;
/*
import com.dosi.auth.AuthenticationRequest;
import com.dosi.auth.AuthenticationResponse;
import com.dosi.auth.RegisterRequest;
import com.dosi.services.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AccountController {
    private final AuthenticationService service;


    /**
     * @param request
     * @return ResponseEntity<AuthenticationResponse>
     */
/*
import org.springframework.web.bind.annotation.PostMapping;

@PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    /**
     * @param request
     * @return ResponseEntity<AuthenticationResponse>
     */
/*
import org.springframework.web.bind.annotation.PostMapping;

@PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));

    }

}
*/