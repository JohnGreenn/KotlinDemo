package com.example.myapplication.util;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @描述： 自定义 Observer
 * @file_name：com.hpkj.yzcj.utils
 * @author：Hujm
 * @time：2018/4/24/024:9:12
 */
public class MyObserver<T> implements Observer<T> {
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
