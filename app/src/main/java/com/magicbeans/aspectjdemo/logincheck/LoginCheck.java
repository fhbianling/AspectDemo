package com.magicbeans.aspectjdemo.logincheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author 边凌
 * date 2017/2/6 15:37
 * desc ${登录检测注解}
 * <p>
 * 切面对应{@link LoginCheckAspect}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginCheck {
}
