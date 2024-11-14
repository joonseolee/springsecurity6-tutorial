package com.joonseolee.practical.controller

import com.joonseolee.practical.domain.dto.AccountDto
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class LoginController {

    @GetMapping(value = ["/login"])
    fun login(@RequestParam(required = false) error: String?,
              @RequestParam(required = false) exception: String?,
              model: Model): String {
        model.addAttribute("error", error)
        model.addAttribute("exception", exception)

        return "login/login"
    }

    @GetMapping(value = ["/api/login"])
    fun restLogin(): String {
        return "rest/login"
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

    @GetMapping(value = ["/denied"])
    fun accessDenied(@RequestParam(required = false) exception: String?,
                     @AuthenticationPrincipal accountDto: AccountDto,
                     model: Model): String {

        model.addAttribute("username", accountDto.username)
        model.addAttribute("exception", exception)

        return "login/denied"
    }
}