package com.joonseolee.security.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.function.Function
import java.util.stream.Collectors


@RestController
class MethodController(
    private val dataService: DataService
) {

    @PostMapping("/writeList")
    fun writeList(@RequestBody data: List<Account>): List<Account> = dataService.writeList(data)

    @PostMapping("/writeMap")
    fun writeMap(@RequestBody data: List<Account>): Map<String, Account> {
        val accountMap = data.stream().collect(Collectors.toMap(Account::owner, Function.identity()))
        return dataService.writeMap(accountMap)
    }

    @GetMapping("/readList")
    fun readList(): List<Account> {
        return dataService.readList()
    }

    @GetMapping("/readMap")
    fun readMap(): Map<String, Account> {
        return dataService.readMap()
    }
}