package com.bian.aspectdebug.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;

/**
 * author 边凌
 * date 2017/4/5 14:27
 * desc ${TODO}
 */

public final class AspectDebugManager {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private LoginCheckClient loginCheckClient;
    private PermissionCheckClient permissionCheckClient;

    private AspectDebugManager() {
        PermissionCheckAspect.setContext(context);
    }

    public static AspectDebugManager getInstance() {
        return Holder.sINSTANCE;
    }

    public static void init(Context context) {
        AspectDebugManager.context = context.getApplicationContext();
    }

    /*---------------------------------------------------------------------*/
    public void setDebugLogEnable(boolean debugLogEnable) {
        MethodLogAspect.setEnabled(debugLogEnable);
    }

    public void registerLoginCheckClient(LoginCheckClient loginCheckClient) {
        this.loginCheckClient = loginCheckClient;
        LoginCheckAspect.setLoginCheckClient(loginCheckClient);
    }

    public void unregisterLoginCheckClient() {
        loginCheckClient = null;
        LoginCheckAspect.setLoginCheckClient(null);
    }

    public void registerPermissionCheckClient(PermissionCheckClient permissionCheckClient) {
        this.permissionCheckClient = permissionCheckClient;
        PermissionCheckAspect.setPermissionCheckClient(permissionCheckClient);
    }

    public void unregisterPermissionCheckClient() {
        this.permissionCheckClient = null;
        PermissionCheckAspect.setPermissionCheckClient(null);
    }
    /*---------------------------------------------------------------------*/

    @SuppressWarnings("WeakerAccess")
    public interface LoginCheckClient {
        /**
         * 当被回调此方法时应返回是否登录成功
         */
        boolean isLoginSuccess();

        /**
         * 当被回调此方法时说明被注解的方法被调用时还未登录
         *
         * @param activity 该方法若有Activity参数则会被传递
         */
        void loginCheckFailed(@Nullable Activity activity);
    }

    @SuppressWarnings("WeakerAccess")
    public interface PermissionCheckClient {
        /**
         * 当被回调此方法时，缺少权限的方法不会被执行，应在此时申请权限
         *
         * @param permission 缺少的权限其数组
         */
        void onPermissionNeeded(String[] permission);
    }

    private static class Holder {
        @SuppressLint("StaticFieldLeak")
        final static AspectDebugManager sINSTANCE = new AspectDebugManager();
    }
}
