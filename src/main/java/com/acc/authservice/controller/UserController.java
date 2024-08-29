package com.acc.authservice.controller;

import com.acc.authservice.model.APIResponse;
import com.acc.authservice.model.request.AuthenticationRequest;
import com.acc.authservice.model.request.RegistrationRequest;
import com.acc.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<APIResponse<RegistrationRequest>> register(@RequestBody RegistrationRequest registrationRequest) {
        return new ResponseEntity<>(authService.registration(registrationRequest), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return new ResponseEntity<>(authService.login(authenticationRequest), HttpStatus.OK);
    }

}
