package com.joonseolee.security.api

import org.springframework.scheduling.annotation.Async
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AsyncService {

    @Async
    fun asyncMethod() {
        val context = SecurityContextHolder.getContextHolderStrategy().context
        println("- securityContext: $context")
        println("- async thread: ${Thread.currentThread().name}")
    }
}