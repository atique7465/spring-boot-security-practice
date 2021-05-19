package com.atique.springbootsecuritypractice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.atique.springbootsecuritypractice.enums.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordConfig passwordConfig;

    @Autowired
    public ApplicationSecurityConfig(PasswordConfig passwordConfig) {
        this.passwordConfig = passwordConfig;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user_01 = User.builder()
                .username("student")
                .password(passwordConfig.passwordEncoder().encode("password"))
                .roles(STUDENT.name())
                .build();

        UserDetails user_02 = User.builder()
                .username("admin")
                .password(passwordConfig.passwordEncoder().encode("password"))
                .roles(ADMIN.name())
                .build();

        UserDetails user_03 = User.builder()
                .username("adminTrainee")
                .password(passwordConfig.passwordEncoder().encode("password"))
                .roles(ADMIN_TRAINEE.name())
                .build();

        return new InMemoryUserDetailsManager(
                user_01,
                user_02,
                user_03
        );
    }
}
