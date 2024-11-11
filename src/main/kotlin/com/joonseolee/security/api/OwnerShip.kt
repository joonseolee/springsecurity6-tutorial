package com.joonseolee.security.api

import org.springframework.security.access.prepost.PostAuthorize

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
@PostAuthorize("returnObject.owner == authentication.name")
annotation class OwnerShip
