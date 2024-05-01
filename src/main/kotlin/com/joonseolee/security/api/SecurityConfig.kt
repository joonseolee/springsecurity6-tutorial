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
        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .formLogin(Customizer.withDefaults())
            .exceptionHandling {
                it
                    .authenticationEntryPoint { _, response, authException ->
                        println("exception -> ${authException!!.message}")
                        response!!.sendRedirect("/login")
                    }
                    // 아래것을 테스트하기위해서는 authenticationEntryPoint 을 주석처리해야한다.
                    // 왜냐 상관은 없는데 login 페이지를 별도로 만들지않았기떄문에 권한별 테스트하기가 현재 구조에서는 어렵기때문
                    .accessDeniedHandler { _, response, accessDeniedException ->
                        println("access exception -> ${accessDeniedException!!.message}")
                        response!!.sendRedirect("/denied")
                    }
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
