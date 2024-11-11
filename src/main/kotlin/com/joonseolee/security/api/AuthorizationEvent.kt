package com.joonseolee.security.api

import org.springframework.context.event.EventListener
import org.springframework.security.authorization.event.AuthorizationDeniedEvent
import org.springframework.security.authorization.event.AuthorizationEvent
import org.springframework.security.authorization.event.AuthorizationGrantedEvent
import org.springframework.stereotype.Component

@Component
class AuthorizationEvent {

    @EventListener
    fun onAuthorization(event: AuthorizationEvent) {
        println("""
            event = ${event.authentication.get().authorities}
        """.trimIndent())
    }

    @EventListener
    fun onAuthorization(failure: AuthorizationDeniedEvent<Unit>) {
        println("""
            failure = ${failure.authentication.get().authorities}
        """.trimIndent())
    }

    @EventListener
    fun onAuthorization(success: AuthorizationGrantedEvent<Unit>) {
        println("""
            success = ${success.authentication.get().authorities}
        """.trimIndent())
    }
}