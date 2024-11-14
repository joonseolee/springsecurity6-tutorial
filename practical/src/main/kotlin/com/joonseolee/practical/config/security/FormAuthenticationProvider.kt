package com.joonseolee.practical.config.security

import com.joonseolee.practical.config.security.exception.SecretException
import com.joonseolee.practical.domain.dto.AccountContext
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class FormAuthenticationProvider(
    private val formUserDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val loginId = authentication.name
        val password = authentication.credentials as String
        val accountContext = formUserDetailsService.loadUserByUsername(loginId) as AccountContext

        if (!passwordEncoder.matches(password, accountContext.password)) {
            throw BadCredentialsException("Invalid password")
        }

        val formAuthenticationDetails = authentication.details as FormAuthenticationDetails
        if (formAuthenticationDetails.secretKey == null || formAuthenticationDetails.secretKey != "secret") {
            throw SecretException("Invalid secret")
        }

        return UsernamePasswordAuthenticationToken(accountContext.accountDto, null, accountContext.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken::class.java)
    }
}