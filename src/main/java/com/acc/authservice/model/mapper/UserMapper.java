package com.acc.authservice.model.mapper;

import com.acc.authservice.entity.User;
import com.acc.authservice.model.request.RegistrationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends APIResponseMapper<User, RegistrationRequest> {

}
