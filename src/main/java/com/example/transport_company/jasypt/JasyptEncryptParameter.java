package com.example.transport_company.jasypt;

import org.springframework.stereotype.Component;

@Component
final class JasyptEncryptParameter {
    final String secretKey = "SecretPassword";
    final String algorithm = "PBEWithMD5AndTripleDES";
    final String iteration = "5000";
    final String poolSize = "4";
    final String providerName = "SunJCE";
    final String saltGenerator = "org.jasypt.salt.RandomSaltGenerator";
    final String OutputType = "base64";
}
