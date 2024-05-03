package com.joonseolee.cors2

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class IndexController {

    @GetMapping("/users")
    fun users(): String {
        val json = """
            {"name": "hong"}
        """.trimIndent()

        return json
    }
}