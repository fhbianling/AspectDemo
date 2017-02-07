package com.magicbeans.aspectjdemo.aty;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.magicbeans.aspectjdemo.R;
import com.magicbeans.aspectjdemo.fastclick.FastClick;

/**
 * author 边凌
 * date 2017/1/19 16:49
 * desc ${TODO}
 */

public class SecondActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
    }

    public void onClick2(View view) {

    }

}
