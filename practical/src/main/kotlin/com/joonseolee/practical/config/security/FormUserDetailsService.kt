package com.joonseolee.practical.config.security

import com.joonseolee.practical.domain.dto.AccountContext
import com.joonseolee.practical.mapper.UserMapper
import com.joonseolee.practical.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class FormUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val account = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("No user")

        val authorities: MutableList<GrantedAuthority> = mutableListOf(SimpleGrantedAuthority(account.roles))
        val accountDto = UserMapper.INSTANCE.toAccountDto(account)

        return AccountContext(accountDto, authorities)
    }
}