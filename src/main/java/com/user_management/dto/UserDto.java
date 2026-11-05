package com.user_management.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer userId;
    private String name;
    private String email;
    private String pwd;
    private String pwdUpdated;
    private String phno;
    private Integer countryId;
    private Integer stateId;
    private Integer cityId;

}
