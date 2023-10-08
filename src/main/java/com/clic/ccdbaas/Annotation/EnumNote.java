package com.clic.ccdbaas.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举类对应注解
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumNote {
    String[] code();

    String[] value();
}
