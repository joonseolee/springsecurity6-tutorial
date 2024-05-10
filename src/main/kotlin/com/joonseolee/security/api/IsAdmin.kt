package com.joonseolee.security.api

import org.springframework.security.access.prepost.PreAuthorize

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
@PreAuthorize("hasRole('ADMIN')")
annotation class IsAdmin
