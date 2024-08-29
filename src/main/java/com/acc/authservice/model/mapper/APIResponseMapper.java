package com.acc.authservice.model.mapper;

import com.acc.authservice.model.APIResponse;

import java.util.List;

public interface APIResponseMapper <S,T>{
    T modelToDto(S source);
    S dtoToModel(T target);

    default APIResponse<T> mapToApiResponse(S source){
        T target = modelToDto(source);
        return new APIResponse<>(target);
    }

    default APIResponse<T> mapErrorToApiResponse(List<String> error){
        return new APIResponse<>(error);
    }
}
