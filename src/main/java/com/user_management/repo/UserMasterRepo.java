package com.user_management.repo;

import com.user_management.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMasterRepo extends JpaRepository<UserMaster, Integer> {
    public Optional<UserMaster> findByEmail(String email);
    public Optional<UserMaster> findByEmailAndPwd(String email, String pwd);
    boolean existsByEmail(String email);
}
