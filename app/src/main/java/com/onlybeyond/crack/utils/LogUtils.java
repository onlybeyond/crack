/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onlybeyond.crack.utils;

import android.text.TextUtils;
import android.util.Log;

import com.onlybeyond.crack.BuildConfig;


public class LogUtils {
    private static String TAG = LogUtils.class.getSimpleName();
    private static final String LOG_PREFIX = "p_";

    public static void LOGD(String tag, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (!TextUtils.isEmpty(tag)) {
            if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.DEBUG)) {
                Log.d(tag, message);
            }
        }
    }

    public static void LOGE(String tag, String message) {
        if (!TextUtils.isEmpty(tag)) {
            Log.e(tag, message);
        }
    }

}
