package com.joonseolee.security.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@EnableWebSecurity
@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/logoutSuccess").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin(Customizer.withDefaults())
            .logout {
                it
                    .logoutUrl("/logoutProc")
                    .logoutRequestMatcher(AntPathRequestMatcher("/logoutProc", "GET"))
                    .logoutSuccessUrl("/logoutSuccess")
                    .logoutSuccessHandler { _, response, _ -> response.sendRedirect("/logoutSuccess") }
                    .deleteCookies("JSESSIONID", "remember-me")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .addLogoutHandler { request, _, _ ->
                        val session = request!!.session
                        session.invalidate()
                        SecurityContextHolder.getContextHolderStrategy().context.authentication = null
                        SecurityContextHolder.getContextHolderStrategy().clearContext()
                    }
                    .permitAll()
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
