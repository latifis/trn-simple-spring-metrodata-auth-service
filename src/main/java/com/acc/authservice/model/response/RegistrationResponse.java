package com.acc.authservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse{
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}

