package com.joonseolee.security.service

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SecurityContextService {

    fun getSecurityContext(): Authentication {
        // 현재 선호되는 방식
        val context = SecurityContextHolder.getContextHolderStrategy().context
        return context.authentication
    }
}