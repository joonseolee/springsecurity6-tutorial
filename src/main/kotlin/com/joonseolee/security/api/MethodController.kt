package com.joonseolee.security.api

import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MethodController {

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    fun admin(): String = "admin"

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    fun user(): String = "user"

    @GetMapping("/isAuthenticated")
    @PreAuthorize("isAuthenticated")
    fun isAuthenticated(): String = "isAuthenticated"

    @GetMapping("/user/{id}")
    @PreAuthorize("#id == authentication.name")
    fun authentication(@PathVariable id: String): String = id

    @GetMapping("/owner")
    @PostAuthorize("returnObject.owner == authentication.name")
    fun owner(name: String): Account = Account(name, false)

    @GetMapping("/isSecure")
    @PostAuthorize("hasAuthority('ROLE_ADMIN') AND returnObject.secure")
    fun isSecure(name: String, secure: String): Account = Account(name, "Y" == secure)
}