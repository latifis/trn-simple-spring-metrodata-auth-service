package com.acc.authservice.service;

import com.acc.authservice.entity.User;
import com.acc.authservice.model.APIResponse;
import com.acc.authservice.model.Role;
import com.acc.authservice.model.mapper.UserMapper;
import com.acc.authservice.model.request.AuthenticationRequest;
import com.acc.authservice.model.request.RegistrationRequest;
import com.acc.authservice.repository.UserRepository;
import com.acc.authservice.util.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ObjectValidator objectValidator;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    public APIResponse registration(RegistrationRequest registrationRequest) {
        List violation = objectValidator.validate(registrationRequest);
        if (!violation.isEmpty()) {
            return userMapper.mapErrorToApiResponse(violation);
        }

        User user = userMapper.dtoToModel(registrationRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER.name());
        return userMapper.mapToApiResponse(
                userRepository.save(user)
        );
    }

//    public APIResponse login(AuthenticationRequest authReq) {
//        UsernamePasswordAuthenticationToken authToken =
//                new UsernamePasswordAuthenticationToken(authReq.getEmail(), authReq.getPassword());
//        authenticationManager.authenticate(authToken);
//        UserDetails userDetails = userDetailsService.loadUserByUsername(authToken.getName());
//        return userMapper.mapTokenToApiResponse(
//                jwtService.generateToken(userDetails)
//        );
//    }
}
