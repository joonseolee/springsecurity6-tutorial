package com.joonseolee.security.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping



@Controller
class ViewController {

    @GetMapping("/method")
    fun method(): String = "method"
}