package com.joonseolee.security.api

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreFilter
import org.springframework.stereotype.Service


@Service
class DataService {

    @PreFilter("filterObject.owner != authentication.name")
    fun writeList(data: List<Account>): List<Account> {
        return data
    }

    @PreFilter("filterObject.value.owner != authentication.name")
    fun writeMap(data: Map<String, Account>): Map<String, Account> {
        return data
    }

    @PostFilter("filterObject.owner != authentication.name")
    fun readList(): List<Account> {
        return listOf(
            Account("user", false),
            Account("db", false),
            Account("admin", false))
    }

    @PostFilter("filterObject.value.owner != authentication.name")
    fun readMap(): Map<String, Account> {
        return mapOf(
            Pair("user", Account("user", false)),
            Pair("db", Account("db", false)),
            Pair("admin", Account("admin", false))
        )
    }
}