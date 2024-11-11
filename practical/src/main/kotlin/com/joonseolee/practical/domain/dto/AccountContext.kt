package com.joonseolee.practical.domain.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AccountContext(
    val accountDto: AccountDto,
    val authorities: MutableList<GrantedAuthority>
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return accountDto.password
    }

    override fun getUsername(): String {
        return accountDto.username
    }
}