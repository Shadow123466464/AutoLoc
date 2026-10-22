package tn.esprit.autoloc.autolocapi.web.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${APP_MANAGER_USERNAME}")
    private String managerUsername;

    @Value("${APP_MANAGER_PASSWORD}")
    private String managerPassword;

    @Value("${APP_USER_USERNAME}")
    private String userUsername;

    @Value("${APP_USER_PASSWORD}")
    private String userPassword;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername(managerUsername)
                        .password(passwordEncoder.encode(managerPassword))
                        .roles("MANAGER")
                        .build(),
                User.withUsername(userUsername)
                        .password(passwordEncoder.encode(userPassword))
                        .roles("USER")
                        .build());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(new CookieCsrfTokenRepository()))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/csrf").authenticated()
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/**").authenticated()
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/**").hasRole("MANAGER")
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/**").hasRole("MANAGER")
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/**").hasRole("MANAGER")
                        .anyRequest().authenticated())
                .httpBasic(httpBasic -> {});

        return http.build();
    }
}
