package com.joonseolee.security.api

import org.springframework.security.core.session.SessionRegistry
import org.springframework.stereotype.Service

@Service
class SessionInfoService(
    private val sessionRegistry: SessionRegistry,
) {
    fun sessionInfo() {
        sessionRegistry.allPrincipals.forEach {
            sessionRegistry.getAllSessions(it, false).forEach { session ->
                println("사용자: $it, 세션아이디: ${session.sessionId}, 최종요청시간: ${session.lastRequest}")
            }
        }
    }
}
