package com.home.employeemanagement.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final PasswordEncoder bCryptEncoder;

    public SecurityConfig(DataSource dataSource, PasswordEncoder bCryptEncoder) {
        this.dataSource = dataSource;
        this.bCryptEncoder = bCryptEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("select username, password, enabled " +
                        "from user_accounts where username = ?")
                .authoritiesByUsernameQuery("select username, role " +
                        "from user_accounts where username = ?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptEncoder);
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/projects/**")
                .hasRole("ADMIN")
                .antMatchers("/**")
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .defaultSuccessUrl("/employees")
                .and()
                .csrf()
                .disable();
    }
}
