package com.joonseolee.practical.repository

import com.joonseolee.practical.domain.dto.Account
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Account, Long> {
}