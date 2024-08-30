package com.acc.authservice.service;

import com.acc.authservice.entity.User;
import com.acc.authservice.entity.UserDetail;
import com.acc.authservice.model.*;
import com.acc.authservice.model.mapper.UserMapper;
import com.acc.authservice.model.request.AuthenticationRequest;
import com.acc.authservice.model.request.RegistrationRequest;
import com.acc.authservice.model.response.RegistrationResponse;
import com.acc.authservice.repository.UserRepository;
import com.acc.authservice.util.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ObjectValidator<RegistrationRequest> validator;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public APIResponse<RegistrationResponse> register(RegistrationRequest registrationRequest) {
        List<String> validate = validator.validate(registrationRequest);
        if (!validate.isEmpty()){
            return userMapper.mapErrorToApiResponseDto(validate);
        }

        User user = userMapper.requestDtoToModel(registrationRequest);
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setRole(Role.USER.name());
        return userMapper.mapToApiResponseDto(
                userRepository.saveAndFlush(user)
        );
    }

    public APIResponse<String> login(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        Authentication authenticate = authenticationManager.authenticate(authToken);
        UserDetail userDetails = (UserDetail) userDetailsService.loadUserByUsername(request.getEmail());
        return userMapper.mapTokenToApiResponse(
                jwtService.generateToken(userDetails)
        );
    }

}
