package com.joonseolee.security.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class ViewController {
    @GetMapping("/cookie")
    fun requestCsrf(): String {
        return "cookie"
    }

    @GetMapping("/form")
    fun form(): String {
        return "form"
    }
}