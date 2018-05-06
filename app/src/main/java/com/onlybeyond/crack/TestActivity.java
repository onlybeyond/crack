package com.onlybeyond.crack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.onlybeyond.crack.utils.LogUtils;

/**
 * Description
 * Created by only on 18/5/1.
 * Email: onlybeyond99@gmail.com
 */

public class TestActivity extends FragmentActivity {
    private static String TAG = "TestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testLog();
    }

    /**
     * 用于测试release版修改debug版
     * release 包不会打印debug级别日志
     */
    private void testLog() {
        LogUtils.LOGD(TAG, "!!!这是一个调试日志");
        LogUtils.LOGE(TAG, "!!!错误类型的日志");
    }

}
