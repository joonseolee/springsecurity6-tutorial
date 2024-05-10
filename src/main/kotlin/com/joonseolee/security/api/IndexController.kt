package com.joonseolee.security.api

import com.joonseolee.security.service.SecurityContextService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping("/custom")
    fun custom(): String {
        return "custom"
    }

    @GetMapping("/admin/db")
    fun admin(): String {
        return "admin"
    }

    @GetMapping("/api/photos")
    fun photos(): String {
        return "photos"
    }

    @GetMapping("/oauth/login")
    fun oauth(): String {
        return "oauth"
    }

    @GetMapping("/user")
    fun user(): String {
        return "user"
    }

    @GetMapping("/db")
    fun db(): String {
        return "db"
    }

    @GetMapping("/admin")
    fun adminP(): String {
        return "adminP"
    }
}
