package com.example.ecomerce.controller;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigInfoControllerTest {

    @Test
    void reportsTheCurrentlyActiveProfile() {
        MockEnvironment environment = new MockEnvironment().setActiveProfiles("prod");

        ConfigInfoController.ConfigInfo info = new ConfigInfoController(environment).getConfigInfo();

        assertThat(info.activeProfile()).isEqualTo("prod");
    }
}
