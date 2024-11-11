package com.joonseolee.practical.controller

import com.joonseolee.practical.domain.dto.Account
import com.joonseolee.practical.domain.entity.AccountDto
import com.joonseolee.practical.service.UserService
import org.modelmapper.ModelMapper
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController(
    val passwordEncoder: PasswordEncoder,
    val userService: UserService
) {
    @PostMapping("/signup")
    fun signup(accountDto: AccountDto): String {
        val mapper = ModelMapper()
        val account = mapper.map(accountDto, Account::class.java)
        account.password = passwordEncoder.encode(accountDto.password)
        userService.createUser(account)

        return "redirect:/"
    }
}