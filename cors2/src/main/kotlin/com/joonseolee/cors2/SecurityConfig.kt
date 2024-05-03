package com.joonseolee.cors2

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.CorsUtils
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                it
                    .anyRequest().permitAll()
            }
            .cors {
                it
                    .configurationSource(corsConfigurationSource())
            }

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration().apply {
            addAllowedOrigin("http://localhost:8080")
            addAllowedMethod("*")
            addAllowedHeader("*")
            allowCredentials = true
            maxAge = 3600L
        }

        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", corsConfiguration)
        }
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
