package com.user_management.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
public class CityDto {
    private Integer cityId;
    private String cityName;
}
