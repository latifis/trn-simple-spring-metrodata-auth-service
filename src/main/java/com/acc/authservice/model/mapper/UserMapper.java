package com.acc.authservice.model.mapper;

import com.acc.authservice.entity.User;
import com.acc.authservice.model.APIResponse;
import com.acc.authservice.model.request.RegistrationRequest;
import com.acc.authservice.model.response.RegistrationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends APIResponseMapper<User, RegistrationRequest, RegistrationResponse>{

    default APIResponse<String> mapTokenToApiResponse (String token) {
        return new APIResponse<>(token);
    }

}
