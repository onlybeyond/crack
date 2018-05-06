package com.onlybeyond.crack;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Description
 * Created by only on 18/5/5.
 * Email: onlybeyond99@gmail.com
 */

public class InsertActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        // 为了能看到页面的交互添加了一个延迟。
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.onlybeyond.crack",
                        "com.onlybeyond.crack.TestTwoActivity"));
                startActivity(intent);
            }
        }, 3000);
    }
}
