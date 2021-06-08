/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.interceptor;

import android.content.Context;
import android.widget.Toast;

import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.subsciber.BaseSubscriber;
import com.zhouyou.http.utils.HttpLog;

import java.lang.ref.WeakReference;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

import static com.zhouyou.http.utils.Utils.isNetworkAvailable;

/**
 * <p>描述：订阅的基类</p>
 * 1.可以防止内存泄露。<br>
 * 2.在onStart()没有网络时直接onCompleted();<br>
 * 3.统一处理了异常<br>
 * 作者： zhouyou<br>
 * 日期： 2016/12/20 10:35<br>
 * 版本： v2.0<br>
 */
public class MyBaseSubscriber<T> extends BaseSubscriber<T> {

    private Context context;

    public MyBaseSubscriber(Context context) {
        super(context);
        this.context = context;
    }

    public void onError(ApiException e) {
        if(e.getCode() == -1000) {//表示异地登录
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


}
