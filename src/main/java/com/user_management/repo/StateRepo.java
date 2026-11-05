package com.user_management.repo;

import com.user_management.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepo extends JpaRepository<State, Integer> {
    public List<State> findByCountryCountryId(Integer countryId);
}
