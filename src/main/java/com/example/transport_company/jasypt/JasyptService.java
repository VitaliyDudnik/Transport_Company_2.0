package com.example.transport_company.jasypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class JasyptService {

    @Autowired
    private JasyptConfiguration configuration;

    public String encrypt(String resource) {
        return configuration.getEncryptor().encrypt(resource);
    }

    public String decrypt(String resource) {
        return configuration.getEncryptor().decrypt(resource);
    }
}
