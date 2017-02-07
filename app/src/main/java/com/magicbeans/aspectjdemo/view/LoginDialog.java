package com.magicbeans.aspectjdemo.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.magicbeans.aspectjdemo.R;

/**
 * author 边凌
 * date 2017/1/19 15:33
 * desc ${TODO}
 */

public class LoginDialog extends AlertDialog{

    public LoginDialog(@NonNull Context context) {
        super(context);
        LayoutInflater from = LayoutInflater.from(context);
        @SuppressLint("InflateParams") View inflate = from.inflate(R.layout.login, null, false);
        setView(inflate);
        inflate.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
