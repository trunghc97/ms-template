package com.example.trunghc.repositories;

import com.example.trunghc.dto.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
