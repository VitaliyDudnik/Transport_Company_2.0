package com.example.transport_company.service;

import com.example.transport_company.entity.Status;
import com.example.transport_company.entity.Token;
import com.example.transport_company.repository.TokenRepository;
import com.example.transport_company.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private StatusService statusService;
    @Autowired
    private AdminService adminService;

    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public boolean existsToken(UUID token) {
        return tokenRepository.existsTokenByUuid(token);
    }

    public Token findTokenByUUID(UUID uuid) {
        return RestPreconditions.checkResource(tokenRepository.findByUuid(uuid));
    }

    public void delete(UUID xToken) {
        Token token = findTokenByUUID(xToken);
        Status status = statusService.findById(4);
        adminService.updateUserStatus(token.getUser().getId(), status.getId());
        tokenRepository.delete(token);
    }

    public void deleteByUserId(long id) {
        tokenRepository.deleteByUserId(id);
    }
}
