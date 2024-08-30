package com.acc.authservice.controller;

import com.acc.authservice.model.APIResponse;
import com.acc.authservice.model.request.AuthenticationRequest;
import com.acc.authservice.model.request.RegistrationRequest;
import com.acc.authservice.model.response.RegistrationResponse;
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
    public ResponseEntity<APIResponse<RegistrationResponse>> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        return new ResponseEntity<>(authService.register(registrationRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> login(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }

}
