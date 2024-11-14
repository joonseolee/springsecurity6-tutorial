package com.joonseolee.practical.config.security

import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationDetailsSource
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.WebAuthenticationDetails

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val formAuthenticationProvider: AuthenticationProvider,
    private val formAuthenticationDetailsSource: AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails>,
    private val successHandler: AuthenticationSuccessHandler,
    private val failureHandler: AuthenticationFailureHandler,
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/css/**", "/images/**", "/js/**", "/favicon.*", "/*/icon-*").permitAll()
                    .requestMatchers("/", "/signup", "/login*").permitAll()
                    .requestMatchers("/user").hasAuthority("ROLE_USER")
                    .requestMatchers("/manager").hasAuthority("ROLE_MANAGER")
                    .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
                    .anyRequest().authenticated()
            }
            .formLogin {
                it
                    .loginPage("/login").permitAll()
                    .authenticationDetailsSource(formAuthenticationDetailsSource)
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
            }
            .authenticationProvider(formAuthenticationProvider)
            .exceptionHandling {
                it
                    .accessDeniedHandler(FormAccessDeniedHandler("/denied"))
            }

        return http.build()
    }

    @Bean
    @Order(1)
    fun restSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .securityMatcher("/api/login")
            .authorizeHttpRequests {
                it
                    .requestMatchers("/css/**", "/images/**", "/js/**", "/favicon.*", "/*/icon-*").permitAll()
                    .anyRequest().permitAll()
            }
            .csrf {
                it
                    .disable()
            }

        return http.build()
    }
}