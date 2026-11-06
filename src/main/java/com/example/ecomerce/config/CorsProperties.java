package com.example.ecomerce.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@ConfigurationProperties(prefix = "app.cors")
@Validated
public record CorsProperties(
        @NotEmpty List<@NotBlank String> allowedOrigins,
        @NotEmpty List<@NotBlank String> allowedMethods,
        @NotEmpty List<@NotBlank String> allowedHeaders,
        @NotNull Boolean allowCredentials
) {
}
