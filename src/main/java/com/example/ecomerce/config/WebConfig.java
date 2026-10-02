package com.example.ecomerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class WebConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
                /// Permitem comunicarea dintre FrontEnd si BackEnd
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                /// Punem restrictii ce endpointuri sunt permitAll si care authenticated
                .authorizeHttpRequests(req -> req
                        .requestMatchers(RESTRICTED_AREA).authenticated()
                        .requestMatchers(WHITE_AREA).permitAll()
                        .anyRequest().permitAll()
                )
                /// setam enpoint pentru login (/authentication) intoarce pagina de logare pentru Get Request
                /// si seteaza in Security Context Holder user-url dupa ce se face Post Request
                .formLogin(form -> form
                        .loginPage("/authentication").permitAll()
                        .defaultSuccessUrl("/").permitAll()
                        .usernameParameter("username")
                );
        return http.build();
    }

    /// Setam encoder pentru a salva parola in format corect, nu RAW
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);

        return source;
    }

    /// enpoint-uri
    private static final String[] WHITE_AREA = {"/user/add", "/authentication", "/login", "/", "/manual/login"};
    private static final String[] RESTRICTED_AREA = {"/user/me", "/resctricted"};
}
