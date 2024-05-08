package com.joonseolee.security.api

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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.csrf.CookieCsrfTokenRepository

@EnableWebSecurity
@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val cookieCsrfTokenRepository = CookieCsrfTokenRepository()
        val spaCsrfTokenRequestHandler = SpaCsrfTokenRequestHandler()

        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/csrf").permitAll()
                    .requestMatchers("/csrfToken").permitAll()
                    .requestMatchers("/form").permitAll()
                    .requestMatchers("/formCsrf").permitAll()
                    .requestMatchers("/cookie").permitAll()
                    .requestMatchers("/cookieCsrf").permitAll()
                    .requestMatchers("/ignoreCsrf").permitAll()
                    .anyRequest().authenticated()
            }
            .csrf {
                it
                    .ignoringRequestMatchers("/ignoreCsrf")
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .csrfTokenRequestHandler(spaCsrfTokenRequestHandler)
            }
            .formLogin(Customizer.withDefaults())
            .addFilterBefore(CsrfCookieFilter(), BasicAuthenticationFilter::class.java)

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
