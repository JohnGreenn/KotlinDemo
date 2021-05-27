package com.example.util

import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observer
import java.util.concurrent.TimeUnit

/**
 * 描述：
 * fileName：com.example.util
 * author：Hujm
 * 添加版本：V4.2.12
 * time：2021/05/26 16:01
 */
object ClickUtil {

    @JvmStatic
    open fun throttleFirst(view: View?, observer: Observer<Any>) {
        view?.let {
            RxView.clicks(view) //两秒钟之内只取一个点击事件，防抖操作
                //                .throttleFirst(1000,TimeUnit.MILLISECONDS)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(observer!!)
        }
    }

    open fun showMobileRegLogin(view: View, num: Int = 1) {

    }

    open fun showUserFindPass(view: View) {

    }

    open fun showMyInfoDetailActivity(view: View) {

    }

    open fun getUserMems(): Int {

        return 1;
    }

    open fun enterJiFenDetail(view: View) {

    }

    open fun enterSignInActivity(view: View) {

    }

    open fun toReturnGoodsList(view: View) {

    }

    open fun enterVip(view: View, mem_status: Int, nickname: String, avatar: String) {

    }

    open fun enterMyAddress(view: View, mem_status: Int, nickname: Boolean, avatar: Boolean) {

    }

    open fun showMyFavorite(view: View, mem_status: Int = 1) {

    }

    open fun showMyCart(view: View) {

    }

    open fun enterShare(view: View) {

    }
    open fun showSetting(view: View) {

    }
}