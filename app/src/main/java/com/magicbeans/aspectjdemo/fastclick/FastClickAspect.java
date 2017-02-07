package com.magicbeans.aspectjdemo.fastclick;

import android.util.LongSparseArray;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * author 边凌
 * date 2017/1/19 15:53
 * desc ${TODO}
 */
@Aspect
class FastClickAspect {
    @Pointcut("execution(@com.magicbeans.aspectjdemo.fastclick.FastClick * *(..))")
    public void withFastClick() {
    }

    private static LongSparseArray<Long> clickTimeStamp = new LongSparseArray<>();
    @Around("withFastClick()")
    public void fastClick(ProceedingJoinPoint joinPoint) throws Throwable {

        ClickConfig clickConfig = new ClickConfig(joinPoint).invoke();
        long clickInterval = clickConfig.getClickInterval();
        FastClick.FilterType filterType = clickConfig.getFilterType();

        boolean viewArgsExist = false;

        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof View) {
                viewArgsExist = true;
                proceedClickAndUpdateClickTimeStamp(joinPoint, clickInterval, filterType, arg);
            }
        }

        if (!viewArgsExist) {
            throw new IllegalArgumentException("被FastClick注解的方法必须有至少一个View参数");
        }
    }

    private void proceedClickAndUpdateClickTimeStamp(ProceedingJoinPoint joinPoint, long clickInterval, FastClick.FilterType filterType, Object arg) throws Throwable {
        int key = arg.hashCode();
        Long old= clickTimeStamp.get(key);
        long now=System.currentTimeMillis();
        if(old==null||now-old>=clickInterval){
            //非快速点击则执行点击事件
            joinPoint.proceed();
            if(filterType== FastClick.FilterType.Invocking){
                clickTimeStamp.put(key,0L);
            }else {
                clickTimeStamp.put(key,now);
            }
        }
    }

    private class ClickConfig {
        private ProceedingJoinPoint joinPoint;
        private long clickInterval;
        private FastClick.FilterType filterType;

        private ClickConfig(ProceedingJoinPoint joinPoint) {
            this.joinPoint = joinPoint;
        }

        private long getClickInterval() {
            return clickInterval;
        }

        private FastClick.FilterType getFilterType() {
            return filterType;
        }

        private ClickConfig invoke() {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();

            clickInterval = FastClick.DEFAULT_INTERVAL;
            filterType = FastClick.FilterType.Click;

            if (method.isAnnotationPresent(FastClick.class)) {
                FastClick annotation = method.getAnnotation(FastClick.class);
                clickInterval = annotation.value();
                filterType = annotation.type();
            }
            return this;
        }
    }
}
