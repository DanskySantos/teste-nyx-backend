package com.nyx.nyx_backend.security.user.repository;

import com.nyx.nyx_backend.security.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndExcludedFalse(String email);

    Optional<User> findByUsernameAndExcludedFalse(String username);
}
