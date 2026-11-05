package com.user_management.repo;

import com.user_management.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepo extends JpaRepository<City, Integer> {
    public List<City> findByStateStateId(Integer stateId);
}
