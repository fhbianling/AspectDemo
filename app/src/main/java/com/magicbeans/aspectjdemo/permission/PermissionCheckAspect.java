package com.magicbeans.aspectjdemo.permission;

import android.content.pm.PackageManager;

import com.magicbeans.aspectjdemo.app.AspectJDemoApp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * author 边凌
 * date 2017/2/7 15:11
 * desc ${权限检查切面}
 */
@Aspect
public class PermissionCheckAspect {

    @Pointcut("execution(@com.magicbeans.aspectjdemo.permission.PermissionCheck * *(..))")
    public void permissionCheckAnnotation() {
    }

    @Around("permissionCheckAnnotation()")
    public void permissionCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (!method.isAnnotationPresent(PermissionCheck.class)) {
            return;
        }

        PermissionCheck annotation = method.getAnnotation(PermissionCheck.class);
        String[] neededPermissions = annotation.value();
        List<String> missingPermissions = new ArrayList<>();

        for (String permission : neededPermissions) {
            if (!hasPermission(permission)) {
                missingPermissions.add(permission);
            }
        }

        if (missingPermissions.size() == 0) {
            joinPoint.proceed();
        }else {
            // TODO: 2017/2/7  
        }
    }


    private PackageManager packageManager = AspectJDemoApp.getAppContext().getPackageManager();
    private String packageName = AspectJDemoApp.getAppContext().getPackageName();

    private boolean hasPermission(String permission) {
        int permissionStatus = packageManager.checkPermission(permission, packageName);
        return PackageManager.PERMISSION_GRANTED == permissionStatus;
    }
}
