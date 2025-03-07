package com.gustavo.guslib.config

import org.springframework.security.access.prepost.PreAuthorize

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@PreAuthorize("hasRole('ROLE_ADMIN')")
annotation class OnlyAdminResource()
