package com.example.transport_company.repository;

import com.example.transport_company.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Transactional(readOnly = true)
    boolean existsTokenByUuid(UUID token);

    @Transactional(readOnly = true)
    Token findByUuid(UUID token);

    void deleteByUserId(long id);
}
