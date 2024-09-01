package com.capstone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user1 = User.builder()
                .username("vijayghadage5555@gmail.com")
                .password(passwordEncoder().encode("vijay@5555"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.builder()
                .username("rohand8552@gmail.com")
                .password(passwordEncoder().encode("rohand@8552"))
                .roles("EMPLOYEE")
                .build();

        UserDetails user3 = User.builder()
                .username("sambhaji5858@gmail.com")
                .password(passwordEncoder().encode("sambhaji@5858"))
                .roles("EMPLOYEE")
                .build();

        UserDetails user4 = User.builder()
                .username("ajay2893@gmail.com")
                .password(passwordEncoder().encode("ajay@2893"))
                .roles("EMPLOYEE")
                .build();

        // Include all users in the InMemoryUserDetailsManager
        return new InMemoryUserDetailsManager(user1, user2, user3, user4);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
