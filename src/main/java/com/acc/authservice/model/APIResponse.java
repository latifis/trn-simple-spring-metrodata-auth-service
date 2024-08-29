package com.acc.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T>{

    private String status = CommonStatus.SUCCESS.name();
    private List<String> message = new ArrayList<>();
    private T data;

    public APIResponse(T data) {
        this.data = data;
    }

    public APIResponse(List<String> message) {
        this.status = CommonStatus.FAILED.name();
        this.message = message;
    }
}