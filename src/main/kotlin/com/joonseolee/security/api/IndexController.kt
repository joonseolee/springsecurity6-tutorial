package com.joonseolee.security.api

import com.joonseolee.security.service.SecurityContextService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController(
    private val securityContextService: SecurityContextService
) {
    @GetMapping
    fun index(): String {
        println(securityContextService.getSecurityContext())

        return "index"
    }

    @GetMapping("/loginPage")
    fun loginPage(): String {
        return "loginPage"
    }

    @GetMapping("/home")
    fun home(): String {
        return "home"
    }

    @PostMapping("/csrf")
    fun csrf(): String {
        return "csrf"
    }

    @PostMapping("/ignoreCsrf")
    fun ignoreCsrf(): String {
        return "ignoreCsrf"
    }
}
