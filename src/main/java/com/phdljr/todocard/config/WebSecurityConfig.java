package com.phdljr.todocard.config;

import com.phdljr.todocard.entity.UserRole.Authority;
import com.phdljr.todocard.security.filter.JwtAuthenticationFilter;
import com.phdljr.todocard.security.filter.JwtAuthorizationFilter;
import com.phdljr.todocard.security.jwt.JwtUtil;
import com.phdljr.todocard.security.userdetails.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter.HeaderValue;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
        throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil);
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return filter;
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtUtil, userDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        // XSS 방지
        http.headers(headers -> headers
            .xssProtection(xss -> xss
                .headerValue(HeaderValue.ENABLED_MODE_BLOCK)
            )
        );

        http.authorizeHttpRequests(authorizeHttpRequests ->
            authorizeHttpRequests
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/api/v1/user/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/cards/**").permitAll()
//                .requestMatchers("/api/v1/admin/**").hasAuthority(Authority.ADMIN)
                .anyRequest().authenticated()
        );

        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
            SessionCreationPolicy.STATELESS));

        http.formLogin(AbstractHttpConfigurer::disable);

        http.addFilterBefore(jwtAuthorizationFilter(), JwtAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
