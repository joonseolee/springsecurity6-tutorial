package com.joonseolee.practical.controller

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
}