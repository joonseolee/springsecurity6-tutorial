package com.joonseolee.practical.controller

import com.joonseolee.practical.domain.dto.AccountContext
import com.joonseolee.practical.domain.dto.AccountDto
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class LoginController {

    @GetMapping(value = ["/login"])
    fun login(): String {
        return "login/login"
    }

    @GetMapping(value = ["/signup"])
    fun signup(): String {
        return "login/signup"
    }

    @GetMapping(value = ["/logout"])
    fun logout(request: HttpServletRequest,
               response: HttpServletResponse): String {
        val authentication = SecurityContextHolder.getContextHolderStrategy().context.authentication
        SecurityContextLogoutHandler().logout(request, response, authentication)
        return "/"
    }
}