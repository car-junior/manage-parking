package com.carjunior.manageparking.domain.controller.test;

import com.carjunior.manageparking.domain.entity.enums.Permission;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class PreAuthorityEvaluator {

    @Around("@annotation(HasPermission)")
    public Object hasAuthority(ProceedingJoinPoint joinPoint) throws Throwable {
        var permission = ((MethodSignature) joinPoint.getSignature()).getMethod()
                .getAnnotation(HasPermission.class)
                .value();

        if (hasPermission(permission))
            return joinPoint.proceed();

        throw new AccessDeniedException("");
    }

    @Around("@annotation(HasAnyPermission)")
    public Object anyAuthority(ProceedingJoinPoint joinPoint) throws Throwable {
        var permissions = ((MethodSignature) joinPoint.getSignature()).getMethod()
                .getAnnotation(HasAnyPermission.class)
                .value();

        if (anyPermission(permissions))
            return joinPoint.proceed();

        throw new AccessDeniedException("");
    }

    @Around("@annotation(HasAllPermission)")
    public Object allAuthority(ProceedingJoinPoint joinPoint) throws Throwable {
        var permissions = ((MethodSignature) joinPoint.getSignature()).getMethod()
                .getAnnotation(HasAllPermission.class)
                .value();

        if (allPermission(permissions))
            return joinPoint.proceed();

        throw new AccessDeniedException("");
    }
    private boolean hasPermission(Permission permission) {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(permission.toString()));
    }

    private boolean anyPermission(Permission[] permissions) {
        var authorities = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .distinct()
                .toList();

        return Arrays.stream(permissions)
                .anyMatch(authorities::contains);
    }

    private boolean allPermission(Permission[] permissions) {
        var authorities = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .distinct()
                .toList();

        return Arrays.stream(permissions)
                .allMatch(authorities::contains);
    }
}
