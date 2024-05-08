package com.joonseolee.security.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.session.MapSession
import org.springframework.session.MapSessionRepository
import org.springframework.session.SessionRepository
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession
import org.springframework.session.web.http.CookieSerializer
import org.springframework.session.web.http.DefaultCookieSerializer
import java.util.concurrent.ConcurrentHashMap

@Configuration
@EnableSpringHttpSession
class HttpSessionConfig {

    @Bean
    fun cookieSerializer(): CookieSerializer {
        return DefaultCookieSerializer().apply {
            setUseHttpOnlyCookie(true)
            setUseSecureCookie(true)
            setSameSite("None")
        }
    }

    @Bean
    fun sessionRepository(): SessionRepository<MapSession> {
        return MapSessionRepository(ConcurrentHashMap())
    }
}