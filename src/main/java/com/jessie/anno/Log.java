package com.jessie.anno;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// 自定义注解
@Retention(RetentionPolicy.RUNTIME) // 运行时，注解生效
@Target(ElementType.METHOD) // 在方法上生效
public @interface Log {

}
