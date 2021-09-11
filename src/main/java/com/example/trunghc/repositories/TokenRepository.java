package com.example.trunghc.repositories;

import com.example.trunghc.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
