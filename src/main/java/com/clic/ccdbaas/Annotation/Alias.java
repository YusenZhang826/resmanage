package com.clic.ccdbaas.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 属性别名自定义注解
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Alias {
    // 数据库对应属性名
    String columnName() default "";

    // 属性别名
    String alias() default "";

    // 业务类型
    String businessType() default "String";

    // 实体类类型
    String type() default "String";

}
