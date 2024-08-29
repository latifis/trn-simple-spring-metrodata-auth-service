package com.acc.authservice.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ObjectValidator<T> {

    private final Validator validator;

    public List<String> validate(T objetToValidate){
        Set<ConstraintViolation<T>> violations = validator.validate(objetToValidate);
        if(!violations.isEmpty()){
            return violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
