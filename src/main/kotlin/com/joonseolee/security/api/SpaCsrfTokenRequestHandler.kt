package com.joonseolee.security.api

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler
import org.springframework.util.StringUtils
import java.util.function.Supplier

class SpaCsrfTokenRequestHandler : CsrfTokenRequestAttributeHandler() {
    private val delegate = XorCsrfTokenRequestAttributeHandler()

    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        deferredCsrfToken: Supplier<CsrfToken>?
    ) {
        delegate.handle(request, response, deferredCsrfToken)
    }

    override fun resolveCsrfTokenValue(request: HttpServletRequest?, csrfToken: CsrfToken?): String {
        if (StringUtils.hasText(request!!.getHeader(csrfToken?.headerName))) {
            return super.resolveCsrfTokenValue(request, csrfToken)
        }

        return delegate.resolveCsrfTokenValue(request, csrfToken)
    }
}