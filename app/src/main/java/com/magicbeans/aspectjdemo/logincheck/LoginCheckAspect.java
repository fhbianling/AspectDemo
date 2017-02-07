package com.magicbeans.aspectjdemo.logincheck;

import android.app.Activity;

import com.magicbeans.aspectjdemo.debug.DebugLog;
import com.magicbeans.aspectjdemo.view.LoginDialog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * author 边凌
 * date 2017/2/6 15:50
 * desc ${LoginCheck注解切面}
 */
@Aspect
class LoginCheckAspect {

    @Pointcut("execution(@com.magicbeans.aspectjdemo.logincheck.LoginCheck * *(..))")
    public void loginCheckAnnotation() {
    }

    /**
     * 这里需要通过joinPoint拿到Activity对象，且判断后续方法是否执行，因此用Around作为Advice
     *
     * @param joinPoint 切入点
     * @throws Throwable
     */
    @Around("loginCheckAnnotation()")
    public void loginCheck(ProceedingJoinPoint joinPoint) throws Throwable {

        if (isLogin()) {
            //登录成功则继续执行后续方法
            joinPoint.proceed();
        } else {
            //若登录不成功则显示登录弹窗，登录逻辑理应被封装在登录弹窗中
            showLoginDialog(joinPoint);
        }
    }

    private void showLoginDialog(ProceedingJoinPoint joinPoint) {

        //通过joinPoint的getTarget()方法可以拿到方法所在类的引用
        //getThis()拿到的是代理对象的引用
        if (joinPoint.getTarget() instanceof Activity) {
            Activity activity = (Activity) joinPoint.getTarget();
            LoginDialog loginDialog = new LoginDialog(activity);
            loginDialog.show();
        }
    }


    /**
     * 模拟登陆是否成功检测方法
     *
     * @return 是否登录成功
     */
    @DebugLog(DebugLog.E)
    private boolean isLogin() {
        return Math.random() > 0.5;
    }

}
