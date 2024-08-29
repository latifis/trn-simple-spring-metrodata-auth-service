package com.acc.authservice.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @NotNull(message = "Firstname should not be empty")
    private String firstname;

    @NotNull(message = "Lastname should not be empty")
    private String lastname;

    @NotNull(message = "Firstname should not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @NotNull(message = "Password should not be empty")
    private String password;
}
