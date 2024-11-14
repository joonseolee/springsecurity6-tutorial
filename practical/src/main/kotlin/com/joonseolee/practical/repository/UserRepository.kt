package com.joonseolee.practical.repository

import com.joonseolee.practical.domain.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Account, Long> {

    fun findByUsername(username: String): Account?
}