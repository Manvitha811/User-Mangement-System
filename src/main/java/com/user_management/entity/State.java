package com.user_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="states_master")
@Getter
@Setter
public class State {
    @Id
    @GeneratedValue
    private  Integer stateId;
    private String stateName;
    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;
}
