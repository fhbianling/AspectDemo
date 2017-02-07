package com.magicbeans.aspectjdemo.app;

import android.app.Application;
import android.content.Context;

/**
 * author 边凌
 * date 2017/2/7 15:18
 * desc ${TODO}
 */

public class AspectJDemoApp extends Application{
    private static Context appContext;

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext=getApplicationContext();
    }
}
