package com.example.myapplication.util;

import android.view.View;

import androidx.databinding.BindingAdapter;

import com.hjq.bar.TitleBar;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 描述：Binding 使用到的工具类
 * 文件名：AdapterUtil.java
 * 作者：Hujm
 * 时间：2018/2/22 0022 10:22
 */
public class AdapterUtil {
    /**
     * 描述：设置列表 点击事件
     * 作者：Hujm
     * 时间：2018/6/26/026 13:19
     * -------------------------------
     */
    @BindingAdapter(value = {"clickview"}, requireAll = false)
    public static void imageLoader(View view,View.OnClickListener listener) {
        ClickUtil.throttleFirst(view,new MyObserver() {
            @Override
            public void onNext(Object o) {
                listener.onClick(view);
            }
        });
    }

    /**
     * 描述：设置头部的标题显示
     * 作者：Hujm
     * 添加版本：V3.0.8
     * 时间：2020/9/7 14:06
     * -------------------------------
     */
    @BindingAdapter(value = {"bartitle"}, requireAll = false)
    public static void TitleBarTitle(TitleBar bar,String bartitle) {
        bar.setTitle(bartitle);
    }


    /**
     * @描述： 自定义 Observer
     * @file_name：com.hpkj.yzcj.utils
     * @author：Hujm
     * @time：2018/4/24/024:9:12
     */
    public static class MyObserver<T> implements Observer<T> {
        public Disposable disposable;

        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(T t) {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }
}