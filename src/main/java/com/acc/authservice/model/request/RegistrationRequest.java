package com.acc.authservice.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @NotBlank(message = "firstname should not be empty")
    private String firstname;

    @NotBlank(message = "lastname should not be empty")
    private String lastname;

    @NotBlank(message = "email should not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9._%+-]+\\.[a-z]{2,3}")
    private String email;

    @NotBlank(message = "password should not be empty")
    private String password;
}
