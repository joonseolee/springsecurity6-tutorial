package com.joonseolee.practical.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping



@Controller
class HomeController {

    @GetMapping(value = ["/"])
    fun dashboard(): String {
        return "/dashboard"
    }

    @GetMapping(value = ["/user"])
    fun user(): String {
        return "/user"
    }

    @GetMapping(value = ["/manager"])
    fun manager(): String {
        return "/manager"
    }

    @GetMapping(value = ["/admin"])
    fun admin(): String {
        return "/admin"
    }
}