package com.example.activity

import com.example.bean.BaseResult
import com.zhouyou.http.callback.CallClazzProxy
import com.zhouyou.http.model.ApiResult
import com.zhouyou.http.request.GetRequest
import com.zhouyou.http.request.PostRequest
import io.reactivex.Observable
import java.lang.reflect.Type


/**
 * 描述：首页-成功案例Fragment
 * 文件：HomeMainFragment.kt
 * 作者：Hujm
 * 添加版本：V3.0.8
 * 时间：2021/5/27 0027 10:58
 */
open class HttpUtile {
    companion object {

        /*添加扩展函数*/
        public fun <T> GetRequest.executeCusB(clazz: Class<T>): Observable<T> {
            return execute(object : CallClazzProxy<BaseResult<T>, T>(clazz) {})
        }

        public fun <T> GetRequest.executeList(type: Type): Observable<T> {
            return execute(object : CallClazzProxy<BaseResult<T>, T>(type) {})
        }

        /*添加扩展函数*/
        public fun <T> PostRequest.executeCusB(clazz: Class<T>): Observable<T> {
            return execute(object : CallClazzProxy<BaseResult<T>, T>(clazz) {})
        }

//        public fun <T> PostRequest.executeCusB(clazz: Class<T>): Observable<T> {
//            return execute(object : CallClazzProxy<BaseResult<T>, T>(clazz) {})
//        }

        public fun <T> PostRequest.executeList(type: Type): Observable<T> {
            return execute(object : CallClazzProxy<BaseResult<T>, T>(type) {})
        }
    }
}