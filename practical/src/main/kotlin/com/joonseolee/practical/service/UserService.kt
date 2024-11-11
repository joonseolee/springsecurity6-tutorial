package com.joonseolee.practical.service

import com.joonseolee.practical.domain.dto.Account
import com.joonseolee.practical.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun createUser(account: Account) {
        userRepository.save(account)
    }
}