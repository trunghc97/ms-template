package com.example.trunghc.repositories;

import com.example.trunghc.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findFirstByUserId(String userId);

    List<User> findAll();

    Optional<User> findById(Long id);
}
