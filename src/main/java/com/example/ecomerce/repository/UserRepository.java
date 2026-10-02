package com.example.ecomerce.repository;

import com.example.ecomerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /// jpa + hibernate -> transforma textul in sql syntax
    User findUserByUsername(String username);
}
