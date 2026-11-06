package com.example.ecomerce.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.config.ConfigDataApplicationContextInitializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

class ProductionConfigurationTest {

    @Test
    void productionStartupFailsWhenJwtSecretIsMissing() {
        new ApplicationContextRunner()
                .withInitializer(new ConfigDataApplicationContextInitializer())
                .withPropertyValues(
                        "spring.profiles.active=prod",
                        "JWT_SECRET=",
                        "JWT_EXPIRATION_MS=3600000",
                        "CORS_ALLOWED_ORIGINS=http://localhost",
                        "CORS_ALLOWED_METHODS=GET",
                        "CORS_ALLOWED_HEADERS=*",
                        "CORS_ALLOW_CREDENTIALS=true"
                )
                .withUserConfiguration(ProductionProperties.class)
                .run(context -> assertThat(context).hasFailed());
    }

    @Configuration(proxyBeanMethods = false)
    @EnableConfigurationProperties({CorsProperties.class, JwtProperties.class})
    static class ProductionProperties {
    }
}
