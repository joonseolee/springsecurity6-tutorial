package com.joonseolee.security.api

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter

class MyCustomDsl : AbstractHttpConfigurer<MyCustomDsl, HttpSecurity>() {

    private var flag: Boolean = false

    override fun init(http: HttpSecurity?) {
        super.init(http)
    }

    override fun configure(http: HttpSecurity?) {
        val myCustomFilter = MyCustomFilter()
        myCustomFilter.setFlag(flag)
        http?.addFilterAfter(myCustomFilter, SecurityContextHolderAwareRequestFilter::class.java)
    }

    fun setFlag(value: Boolean): Boolean {
        flag = value

        return flag
    }

    companion object {

        fun customDsl(): MyCustomDsl {
            return MyCustomDsl()
        }
    }
}