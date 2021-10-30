package com.example.transport_company.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfiguration {

    @Autowired
    private JasyptEncryptParameter parameter;

    public StringEncryptor getEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(parameter.secretKey);
        config.setAlgorithm(parameter.algorithm);
        config.setKeyObtentionIterations(parameter.iteration);
        config.setPoolSize(parameter.poolSize);
        config.setProviderName(parameter.providerName);
        config.setSaltGeneratorClassName(parameter.saltGenerator);
        config.setStringOutputType(parameter.OutputType);
        encryptor.setConfig(config);
        return encryptor;
    }
}
