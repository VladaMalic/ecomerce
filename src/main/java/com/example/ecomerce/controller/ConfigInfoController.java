package com.example.ecomerce.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigInfoController {
    private final Environment environment;

    public ConfigInfoController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/api/config/info")
    public ConfigInfo getConfigInfo() {
        String[] activeProfiles = environment.getActiveProfiles();
        String profile = activeProfiles.length > 0
                ? String.join(",", activeProfiles)
                : String.join(",", environment.getDefaultProfiles());
        return new ConfigInfo(profile);
    }

    public record ConfigInfo(String activeProfile) {
    }
}
