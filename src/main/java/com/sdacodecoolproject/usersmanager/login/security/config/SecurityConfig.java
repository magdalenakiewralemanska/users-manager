package com.sdacodecoolproject.usersmanager.login.security.config;

import com.sdacodecoolproject.usersmanager.login.constant.SecurityConstant;
import com.sdacodecoolproject.usersmanager.login.security.token.AuthorizationFilter;
import com.sdacodecoolproject.usersmanager.login.security.token.messages.AccessDeniedMessage;
import com.sdacodecoolproject.usersmanager.login.security.token.messages.AuthenticationForbiddenMessage;
import com.sdacodecoolproject.usersmanager.login.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthorizationFilter authorizationFilter;
    private final AuthenticationForbiddenMessage forbiddenMessage;
    private final AccessDeniedMessage accessDeniedMessage;
    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public SecurityConfig(AuthorizationFilter authorizationFilter, AuthenticationForbiddenMessage forbiddenMessage,
                          AccessDeniedMessage accessDeniedMessage, UserServiceImpl userService, BCryptPasswordEncoder passwordEncoder) {
        this.authorizationFilter = authorizationFilter;
        this.forbiddenMessage = forbiddenMessage;
        this.accessDeniedMessage = accessDeniedMessage;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder).and().build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests().requestMatchers(SecurityConstant.PUBLIC_URLS).permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(accessDeniedMessage)
                .authenticationEntryPoint(forbiddenMessage)
                .and().addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
