package com.joonseolee.security.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {
    @GetMapping
    fun index(): String {
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

    @GetMapping("/logoutSuccess")
    fun logoutSuccess(): String {
        return "logoutSuccess"
    }
}
