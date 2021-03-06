package com.example.fragment

import com.example.R
import com.example.activity.HttpUtile.Companion.executeCusB
import com.example.bean.UserData
import com.example.databinding.FragmentHomeMyBinding
import com.example.interceptor.MyBaseSubscriber
import com.zhouyou.http.EasyHttp


/**
 * 描述：首页-我的Fragment
 * 文件：HomeMainFragment.kt
 * 作者：Hujm
 * 添加版本：V3.0.8
 * 时间：2021/5/27 0027 10:58
 */
class HomeMyFragment : LibraryLazyFragment<FragmentHomeMyBinding, UserData>() {
    override fun getContentView(): Int {
        return R.layout.fragment_home_my
    }

    override fun lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return
        }
        userLogin()
//        mHasLoadedOnce = true
    }

    override fun initBundleData() {
    }

    override fun initView() {
    }


    private fun userLogin() {
        observable = EasyHttp.post("/v2/jiekou/myUc")
//            .headers("token", encryptedPreferences?.getString("token", ""))
            .executeCusB(UserData::class.java)
        observable.subscribe(object : MyBaseSubscriber<UserData>(activity) {
            override fun onNext(data: UserData) {
                binding.data = data
            }
        })
    }
}