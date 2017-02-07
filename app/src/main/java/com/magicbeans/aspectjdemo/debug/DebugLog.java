package com.magicbeans.aspectjdemo.debug;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法耗时日志注解
 * 打印的信息包括方法参数值，返回值，
 * 方法耗时
 * 可传参设置日志等级
 * 注意，调用{@link DebugLogAspect#setEnabled(boolean)}方法可关闭日志输出
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DebugLog {
    int V = 1;
    int D = 2;
    int I = 3;
    int E = 4;

    int value() default V;
}
