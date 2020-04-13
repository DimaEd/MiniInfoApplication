package com.ednach.config;

import com.ednach.security.filter.AuthenticationTokenFilter;
import com.ednach.service.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                .authorizeRequests()
                .mvcMatchers("/authentication/signIn", "/authentication/refresh").permitAll()
                .mvcMatchers("/roles/**", "/users/**", "/product/**", "/producer/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().hasRole("ADMIN");
        http.addFilterBefore(new AuthenticationTokenFilter(tokenService, userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }
}