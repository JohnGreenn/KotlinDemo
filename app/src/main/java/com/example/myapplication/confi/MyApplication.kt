package com.example.myapplication.confi

import android.app.Application
import com.example.myapplication.BuildConfig
import com.google.gson.Gson
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.cache.converter.GsonDiskConverter
import com.zhouyou.http.cache.converter.SerializableDiskConverter
import com.zhouyou.http.model.HttpHeaders
import com.zhouyou.http.model.HttpParams
import org.jetbrains.anko.asyncResult
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 描述：
 * fileName：com.example.myapplication.confi
 * author：Hujm
 * 添加版本：V4.2.12
 * time：2021/05/13 13:57
 */

open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //网络初始化
        EasyHttp.init(this)

        //以下设置的所有参数是全局参数，同样的参数可以在请求的时候再设置一遍，那么对于该请求来说，请求中的参数会覆盖全局参数
        EasyHttp.getInstance()
            //可以全局统一设置全局URL
            .setBaseUrl(BuildConfig.BaseURL)
            /*是否开启调试*/
            .debug("EasyHttp", true)
            .setReadTimeOut(60 * 1000)
            .setWriteTimeOut(60 * 1000)
            .setConnectTimeout(60 * 1000)
            /*网络不好时，自动重连次数*/
            .setRetryCount(3)
//            可以全局统一设置超时重试间隔时间,默认为500ms,不需要可以设置为0
//            .setRetryDelay()
//            .cacheDiskConverter(GsonDiskConverter())//GSON-数据转换器
//            .setCacheDiskConverter(SerializableDiskConverter())//GSON-数据转换器
//            .setCacheDiskConverter(GsonDiskConverter())//GSON-数据转换器

            .addConverterFactory(GsonConverterFactory.create(Gson()))
//            .addCommonHeaders(headers)//设置全局公共头
//            .addCommonParams(params)//设置全局公共参数
//            .setOkconnectionPool()//设置请求连接池
//            .addInterceptor()


    }


}