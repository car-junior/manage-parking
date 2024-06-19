package com.carjunior.manageparking.domain.controller.test;


import com.carjunior.manageparking.domain.entity.enums.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface HasAllPermission {
    Permission[] value();
}
