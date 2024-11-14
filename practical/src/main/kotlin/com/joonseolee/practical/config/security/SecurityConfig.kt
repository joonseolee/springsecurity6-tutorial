package com.joonseolee.practical.config.security

import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationDetailsSource
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.WebAuthenticationDetails

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val formAuthenticationProvider: AuthenticationProvider,
    private val formAuthenticationDetailsSource: AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails>
) {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/css/**", "/images/**", "/js/**", "/favicon.*", "/*/icon-*").permitAll()
                    .requestMatchers("/", "/signup").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin {
                it
                    .loginPage("/login").permitAll()
                    .authenticationDetailsSource(formAuthenticationDetailsSource)
            }
            .authenticationProvider(formAuthenticationProvider)

        return http.build()
    }
}