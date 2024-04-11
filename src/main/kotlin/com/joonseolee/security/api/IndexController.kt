package com.joonseolee.security.api

import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.CurrentSecurityContext
import org.springframework.security.core.context.SecurityContext
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

    @GetMapping("/anonymous")
    fun anonymous(): String {
        return "anonymous"
    }

    /**
     * 익명사용자라고 한들 auth 는 초기화되지않기때문에
     * 그 아래 anonymousContext API 처럼 Resolver 를 사용해야한다.
     */
    @GetMapping("/authentication")
    fun authentication(auth: Authentication): String {
        return if (auth is AnonymousAuthenticationToken) {
            "anonymous"
        } else {
            "null"
        }
    }

    @GetMapping("/anonymousContext")
    fun anonymousContext(
        @CurrentSecurityContext context: SecurityContext,
    ): String {
        return context.authentication.name
    }
}
