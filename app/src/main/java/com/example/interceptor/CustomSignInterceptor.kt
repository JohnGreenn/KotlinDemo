package com.example.interceptor

import android.util.Log
import android.widget.Toast
import com.zhouyou.http.interceptor.BaseDynamicInterceptor
import com.zhouyou.http.interceptor.GzipRequestInterceptor
import java.util.*
import kotlin.math.log

/**
 * 描述：
 * fileName：com.example.interceptor
 * author：Hujm
 * 添加版本：V4.2.12
 * time：2021/06/01 10:18
 */
public class CustomSignInterceptor : BaseDynamicInterceptor<CustomSignInterceptor>() {
    override fun dynamic(dynamicMap: TreeMap<String, String>): TreeMap<String, String> {
        Log.i("cc", "dd")
        return dynamicMap;
    }


}