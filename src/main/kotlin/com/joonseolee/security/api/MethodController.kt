package com.joonseolee.security.api

import jakarta.annotation.security.DenyAll
import jakarta.annotation.security.PermitAll
import jakarta.annotation.security.RolesAllowed
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.function.Function
import java.util.stream.Collectors


@RestController
class MethodController(
    private val dataService: DataService
) {

    @GetMapping("/user")
    @Secured("ROLE_USER")
    fun user(): String = "user"

    @GetMapping("/admin")
    @RolesAllowed("ADMIN")
    fun admin(): String = "admin"

    @GetMapping("/permitAll")
    @PermitAll
    fun permitAll(): String = "permitAll"

    @GetMapping("/denyAll")
    @DenyAll
    fun denyAll(): String = "denyAll"

    @GetMapping("/isAdmin")
    @IsAdmin
    fun isAdmin(): String = "isAdmin"

    @GetMapping("/ownerShip")
    @OwnerShip
    fun ownerShip(name: String): Account = Account(name, false)

    @GetMapping("/delete")
    @PreAuthorize("@myAuthorizer.isUser(#root)")
    fun delete(): String = "delete"
}