package com.capstone.config;


import com.capstone.security.JwtAuthenticationEntryPoint;
import com.capstone.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(auth -> auth
                // Allow access to Swagger endpoints without authentication
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/auth/login/**").permitAll()
                // Specific endpoint access for EMPLOYEE
                .requestMatchers(HttpMethod.GET, "/api/employees/{id}").hasAnyRole("EMPLOYEE", "ADMIN")
                .requestMatchers("/api/employees/department/{id}", 
                                 "/api/employees/tasks/{id}", 
                                 "/api/employees/performance/{id}").hasAnyRole("EMPLOYEE", "ADMIN")
                // Restrict /api/employees to ADMIN only
             //   .requestMatchers(HttpMethod.POST, "/api/employees/**").hasRole("ADMIN")
              //  .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("ADMIN")
              //  .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
               // .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("ADMIN")
                // All other requests require ADMIN
                .anyRequest().hasRole("ADMIN")
                
            )
            .exceptionHandling(ex -> {
                ex.authenticationEntryPoint(point)
                  .accessDeniedHandler(point); // Set custom access denied handler
            })
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Using AuthenticationConfiguration to provide the AuthenticationManager
        return authenticationConfiguration.getAuthenticationManager();
    }
}
