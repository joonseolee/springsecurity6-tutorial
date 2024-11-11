package com.joonseolee.practical.domain.entity

data class AccountDto(
    val id: String?,
    val username: String,
    val password: String,
    val age: Int,
    val roles: String
)
