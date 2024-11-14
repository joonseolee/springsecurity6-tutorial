package com.joonseolee.practical.controller

import com.joonseolee.practical.domain.dto.AccountDto
import com.joonseolee.practical.mapper.UserMapper
import com.joonseolee.practical.service.UserService
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
        val account = UserMapper.INSTANCE.toAccount(accountDto)
        account.password = passwordEncoder.encode(accountDto.password)
        userService.createUser(account)

        return "redirect:/"
    }
}