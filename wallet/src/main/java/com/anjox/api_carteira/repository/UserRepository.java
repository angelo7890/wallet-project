package com.anjox.api_carteira.repository;

import com.anjox.api_carteira.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByemail(String email);
    UserEntity findBytoken(String token);
    UserEntity findByid(Long id);
}
