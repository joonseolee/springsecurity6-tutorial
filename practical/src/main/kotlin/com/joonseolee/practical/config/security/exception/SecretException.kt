package com.joonseolee.practical.config.security.exception

import org.springframework.security.core.AuthenticationException

class SecretException(
    message: String
) : AuthenticationException(message)