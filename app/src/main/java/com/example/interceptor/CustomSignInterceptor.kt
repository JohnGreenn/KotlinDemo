package com.example.interceptor

import com.example.confi.MyApplication.Companion.encryptedPreferences
import com.zhouyou.http.interceptor.BaseDynamicInterceptor
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

/**
 * 描述：自定义参数拦截器
 * fileName：com.example.interceptor
 * author：Hujm
 * 添加版本：V4.2.12
 * time：2021/06/01 10:18
 */
public class CustomSignInterceptor : BaseDynamicInterceptor<CustomSignInterceptor>() {
    override fun dynamic(dynamicMap: TreeMap<String, String>): TreeMap<String, String> {
        val token = encryptedPreferences?.getString("token", "")
        token?.let { dynamicMap.put("token", it) }
        return dynamicMap;
    }
}