package com.magicbeans.aspectjdemo.fastclick;

import android.util.Log;
import android.util.LongSparseArray;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * author 边凌
 * date 2017/1/19 15:53
 * desc ${TODO}
 */
@Aspect
public class FastClickAspect {
    @Pointcut("execution(@com.magicbeans.aspectjdemo.fastclick.FastClick * *(..))")
    public void withFastClick() {
    }

    @Around("withFastClick()")
    public void fastClick(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        boolean checkParameter = false;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        long value = FastClick.DEFAULT_INTERVAL;
        FastClick.FilterType type = FastClick.FilterType.Click;
        if (method.isAnnotationPresent(FastClick.class)) {
            FastClick annotation = method.getAnnotation(FastClick.class);
            value = annotation.value();
            type = annotation.type();
        }
        for (Object arg : args) {
            if (arg instanceof View) {
                checkParameter = true;
                if (processView((View) arg, value)) {
                    joinPoint.proceed();
                    if (type == FastClick.FilterType.Invocking) {
                        array.put(arg.hashCode(), 0L);
                    }
                }
            }
        }

        if (!checkParameter) {
            throw new IllegalArgumentException("被FastClick注解的方法必须有至少一个View参数");
        }
    }

    private static LongSparseArray<Long> array = new LongSparseArray<>();

    private boolean processView(View arg, long defaultInterval) {
        Long old = array.get(arg.hashCode());

        long now = System.currentTimeMillis();

        array.put(arg.hashCode(), now);
        if (old == null) {
            array.put(arg.hashCode(), now);
            return true;
        }
        return now - old >= defaultInterval;
    }
}
