package com.joonseolee.practical.config.security

import com.joonseolee.practical.config.security.exception.SecretException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler

@Configuration
class FormAuthenticationFailureHandler : SimpleUrlAuthenticationFailureHandler() {

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        val errorMessage = acquireExceptionMessage(exception)

        setDefaultFailureUrl("/login?error=true&exception=$errorMessage")

        super.onAuthenticationFailure(request, response, exception)
    }

    private fun acquireExceptionMessage(exception: AuthenticationException): String {
        if (exception is BadCredentialsException) {
            return "Invalid username or password"
        }

        if (exception is UsernameNotFoundException) {
            return "User not exist"
        }

        if (exception is CredentialsExpiredException) {
            return "Expired password"
        }

        if (exception is SecretException) {
            return "Invalid Secret key"
        }

        return ""
    }
}