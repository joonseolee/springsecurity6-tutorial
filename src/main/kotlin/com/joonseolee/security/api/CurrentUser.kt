package com.joonseolee.security.api

import org.springframework.security.core.annotation.AuthenticationPrincipal
import java.lang.annotation.ElementType
import java.lang.annotation.Target

@kotlin.annotation.Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@AuthenticationPrincipal
annotation class CurrentUser
