package com.example.letsplay.user.repository;

import com.example.letsplay.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);
}
