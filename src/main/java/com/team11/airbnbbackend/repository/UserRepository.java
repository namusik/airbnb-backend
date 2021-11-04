package com.team11.airbnbbackend.repository;

import com.team11.airbnbbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String nickname);

    Optional<User> findByEmail(String email);

}
