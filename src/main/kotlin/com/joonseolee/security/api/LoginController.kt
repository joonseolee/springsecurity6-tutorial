package com.joonseolee.security.api

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val authenticationManager: AuthenticationManager
) {
    val securityContextRepository = HttpSessionSecurityContextRepository()

    @PostMapping("/login")
    fun login(@RequestBody login: LoginRequest, request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val token = UsernamePasswordAuthenticationToken(login.username, login.password)
        val authentication = authenticationManager.authenticate(token)
        val securityContext = SecurityContextHolder.getContextHolderStrategy().createEmptyContext()
        securityContext.authentication = authentication
        SecurityContextHolder.getContextHolderStrategy().context = securityContext

        securityContextRepository.saveContext(securityContext, request, response)

        return authentication
    }
}