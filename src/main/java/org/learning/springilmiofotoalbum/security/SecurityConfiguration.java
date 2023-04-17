package org.learning.springilmiofotoalbum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    UserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        // creo un authentication provider basato su database (DAO)
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // associo il mio DatabaseUserDetailsService
        provider.setUserDetailsService(userDetailsService());

        // associo il mio PasswordEncoder
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/error/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
                .requestMatchers("/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/images/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/categories/**").hasAuthority("ADMIN")
                .and().formLogin()
                .and().logout()
                .and().exceptionHandling();
        http.csrf().disable();
        return http.build();
    }
}
