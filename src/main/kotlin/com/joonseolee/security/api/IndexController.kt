package com.joonseolee.security.api

import com.joonseolee.security.service.SecurityContextService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private

@RestController
class IndexController(
    private val securityContextService: SecurityContextService,
    private val dataService: DataService
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

    @GetMapping("/custom")
    fun custom(): String {
        return "custom"
    }

    @GetMapping("/admin/db")
    fun admin(): String {
        return "admin"
    }

    @GetMapping("/api/photos")
    fun photos(): String {
        return "photos"
    }

    @GetMapping("/oauth/login")
    fun oauth(): String {
        return "oauth"
    }


    @GetMapping("/db")
    fun db(): String {
        return "db"
    }

    @GetMapping("/admin")
    fun adminP(): String {
        return "adminP"
    }

    @GetMapping("/user")
    fun user(): String {
        return dataService.getUser()
    }

    @GetMapping("/owner")
    fun owner(name: String): Account {
        return dataService.getOwner(name)
    }

    @GetMapping("/display")
    fun display(): String {
        return dataService.display()
    }

    @GetMapping("/users")
    fun users(request: HttpServletRequest, response: HttpServletResponse): List<MemberDto> {
        val authenticate = request.authenticate(response)
        if (authenticate) {
            return listOf(MemberDto("user", "1111"))
        }

        return listOf()
    }

    @GetMapping("/login")
    fun login(request: HttpServletRequest, memberDto: MemberDto): String {
        request.login(memberDto.name, memberDto.password)
        println("login success!")

        return "login"
    }
}
