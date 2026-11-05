package com.user_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="country_master")
@Setter
@Getter
public class Country {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY)
    private Integer countryId;
    private String name;
}
