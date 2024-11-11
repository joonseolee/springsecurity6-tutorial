package com.joonseolee.practical.mapper

import com.joonseolee.practical.domain.dto.AccountDto
import com.joonseolee.practical.domain.entity.Account
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface UserMapper {

    fun toAccountDto(account: Account): AccountDto

    @InheritInverseConfiguration
    fun toAccount(accountDto: AccountDto): Account

    companion object {
        val INSTANCE = Mappers.getMapper(UserMapper::class.java)
    }
}