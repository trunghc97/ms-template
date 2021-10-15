package com.example.trunghc.repositories;

import com.example.trunghc.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String token);

    Token findByUserIdAndDeleted(Long userId, boolean isDelete);
}
