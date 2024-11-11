package com.joonseolee.security.api

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

class MyCustomFilter : OncePerRequestFilter() {

    private var flag: Boolean = false

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (flag) {
            try {
                val username = request.getParameter("username")
                val password = request.getParameter("password")

                request.login(username, password)
            } catch (e: Exception) {
                println(e)
            }
        }

        filterChain.doFilter(request, response)
    }

    fun setFlag(value: Boolean) {
        flag = value
    }

}
