package com.joonseolee.security.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.savedrequest.HttpSessionRequestCache

@EnableWebSecurity
@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val requestCache = HttpSessionRequestCache()
        requestCache.setMatchingRequestParameterName("customParam=y")

        http
            .authorizeHttpRequests { it.anyRequest().authenticated() }
            .formLogin {
                it
                    .successHandler { request, response, _ ->
                        val savedRequest = requestCache.getRequest(request, response)
                        val redirectUrl = savedRequest.redirectUrl
                        response.sendRedirect(redirectUrl)
                    }
            }
            .requestCache {
                it
                    .requestCache(requestCache)
            }

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
