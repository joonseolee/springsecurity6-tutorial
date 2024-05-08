package com.joonseolee.security.api

import com.joonseolee.security.service.SecurityContextService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
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

    @PostMapping("/csrf")
    fun csrf(): String {
        return "csrf"
    }

    @PostMapping("/ignoreCsrf")
    fun ignoreCsrf(): String {
        return "ignoreCsrf"
    }

    @GetMapping("/csrfToken")
    fun csrfToken(request: HttpServletRequest): String {
        val csrfToken1: CsrfToken = request.getAttribute(CsrfToken::class.java.name) as CsrfToken
        val csrfToken2: CsrfToken = request.getAttribute("_csrf") as CsrfToken

        return """
            csrfToken1 = ${csrfToken1.token},
            csrfToken2 = ${csrfToken2.token}
        """.trimIndent()
    }

    @PostMapping("/formCsrf")
    fun formCsrf(csrfToken: CsrfToken): CsrfToken {
        return csrfToken
    }

    @PostMapping("/cookieCsrf")
    fun cookieCsrf(csrfToken: CsrfToken): CsrfToken {
        return csrfToken
    }
}
