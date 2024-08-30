package com.acc.authservice.model.mapper;

import com.acc.authservice.model.APIResponse;

import java.util.List;

/**
 *
 * @Generic <M> Model Object
 * @Generic <R> RequestDTO Object
 * @Generic <S> ResponseDTO Object
 */

public interface APIResponseMapper<M, R, S>{
    M requestDtoToModel (R r);
    S modelToResponseDto (M m);
    List<S> listModelToResponseDtoList (List<M> m);

    default APIResponse<S> mapToApiResponseDto (M m) {
        S target = modelToResponseDto(m);
        return new APIResponse<>(target);
    }

    default APIResponse<List<S>> mapToApiResponseListDto (List<M> m) {
        List<S> target = listModelToResponseDtoList(m);
        return new APIResponse<>(target);
    }

    default APIResponse<S> mapErrorToApiResponseDto (List<String> error) {
        return new APIResponse<>(error);
    }

}
