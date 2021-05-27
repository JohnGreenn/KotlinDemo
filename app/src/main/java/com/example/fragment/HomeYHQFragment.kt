package com.example.fragment

import com.example.R
import com.example.databinding.FragmentHomeYhqBinding


/**
 * 描述：首页-优惠券Fragment
 * 文件：HomeMainFragment.kt
 * 作者：Hujm
 * 添加版本：V3.0.8
 * 时间：2021/5/27 0027 10:58
 */
public class HomeYHQFragment : LibraryLazyFragment<FragmentHomeYhqBinding,String>() {
    override fun getContentView(): Int {
        return R.layout.fragment_home_yhq
    }

    override fun lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return
        }
        mHasLoadedOnce = true
    }

    override fun initBundleData() {
    }

    override fun initView() {
    }
}