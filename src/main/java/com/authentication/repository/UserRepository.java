package com.authentication.repository;

import com.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    void deleteByName(String name);
    Optional<User> findByNameAndPassword(String name, String password);
}
