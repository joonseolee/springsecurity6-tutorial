package com.joonseolee.practical.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class RestLoginController {

    @PostMapping(value = ["/api/login"])
    fun restLogin(): String {
        return "restLogin"
    }
}