package com.joonseolee.practical.domain.dto

data class AccountDto(
    val id: Long?,
    val username: String,
    val password: String,
    val age: Int,
    val roles: String
)
