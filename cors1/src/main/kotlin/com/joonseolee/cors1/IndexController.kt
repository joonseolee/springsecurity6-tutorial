package com.joonseolee.cors1

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RestController

@Controller
class IndexController {

    fun index(): String {
        return "index"
    }
}