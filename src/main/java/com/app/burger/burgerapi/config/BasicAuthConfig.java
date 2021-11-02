package com.app.burger.burgerapi.config;

import com.app.burger.burgerapi.repo.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(1)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BasicAuthConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(com.app.burger.burgerapi.repo.models.User.PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .requestMatchers()
                .antMatchers("/burgers", "/burgers/**", "/ingredients", "/ingredients/**")
                .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/ingredients", "/ingredients/**").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/ingredients", "/ingredients/**").hasAuthority(Role.ADMIN.name())
                .anyRequest().authenticated()
                .and()
            .httpBasic()
                .and()
            .csrf().disable()
        .cors();
    }
}
