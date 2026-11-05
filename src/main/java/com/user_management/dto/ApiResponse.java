package com.user_management.dto;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private Integer status;
    private String msg;
    private T payload;
}
