package com.team11.airbnbbackend.repository;

import com.team11.airbnbbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
