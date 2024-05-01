package com.joonseolee.security.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RegexRequestMatcher
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

@EnableWebSecurity
@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        introspector: HandlerMappingIntrospector,
    ): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/user").hasAuthority("ROLE_USER")
                    .requestMatchers("/myPage/**").hasRole("USER")
                    .requestMatchers(HttpMethod.POST).hasAuthority("ROLE_WRITE")
                    .requestMatchers(AntPathRequestMatcher("/manager/**")).hasAuthority("ROLE_MANAGER")
                    .requestMatchers(MvcRequestMatcher(introspector, "/admin/payment")).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(RegexRequestMatcher("/resource/[A-Za-z0-9]+", null)).hasAuthority("ROLE_MANAGER")
                    .anyRequest().authenticated()
            }
            .formLogin(Customizer.withDefaults())
            .csrf {
                it
                    .disable()
            }

        return http.build()
    }

    /**
     * 해당 빈이 있을경우 application.yml 파일보다 우선순위가 높다
     */
    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.withUsername("user").password("{noop}1111").roles("USER").build()
        val manager = User.withUsername("manager").password("{noop}1111").roles("MANAGER").build()
        val admin = User.withUsername("admin").password("(noop}1111").roles("ADMIN", "WRITE").build()
        return InMemoryUserDetailsManager(user, manager, admin)
    }
}
