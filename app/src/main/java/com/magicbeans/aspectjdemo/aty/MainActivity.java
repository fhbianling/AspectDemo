package com.magicbeans.aspectjdemo.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.magicbeans.aspectjdemo.R;
import com.magicbeans.aspectjdemo.debug.DebugLog;
import com.magicbeans.aspectjdemo.fastclick.FastClick;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        tv.setText(0 + "");
    }

    @FastClick(type = FastClick.FilterType.Invocking)
    public void onClick(View view) {
        count++;
        tv.setText("onClick:" + count);
    }

    @DebugLog(DebugLog.I)
    @FastClick(type = FastClick.FilterType.Invocking)
    public void onClick2(View v){
        startActivity(new Intent(this,SecondActivity.class));
    }
}
