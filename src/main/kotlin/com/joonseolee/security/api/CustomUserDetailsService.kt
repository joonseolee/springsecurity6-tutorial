package com.joonseolee.security.api

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class CustomUserDetailsService : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails? {
        return User
            .withUsername("user")
            .password("{noop}1111")
            .roles("USER")
            .build()
    }
}
