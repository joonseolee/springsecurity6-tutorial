package com.joonseolee.security.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        // 방법 1
//        val builder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
//        builder.authenticationProvider(CustomAuthenticationProvider())
//        builder.authenticationProvider(CustomAuthenticationProvider2())

        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin(Customizer.withDefaults())
            // 방법 2
            .authenticationProvider(CustomAuthenticationProvider())
            .authenticationProvider(CustomAuthenticationProvider2())

        return http.build()
    }

    /**
     * 해당 빈이 있을경우 application.yml 파일보다 우선순위가 높다
     */
    @Bean
    fun userDetailsService(): UserDetailsService {
        val user =
            User
                .withUsername("user")
                .password("{noop}1111")
                .roles("USER").build()

        return InMemoryUserDetailsManager(user)
    }
}
