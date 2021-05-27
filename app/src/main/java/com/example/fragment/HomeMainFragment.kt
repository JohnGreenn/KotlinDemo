package com.example.fragment

import com.example.R
import com.example.databinding.FragmentHomeMainBinding


/**
 * 描述：首页-首页Fragment
 * 文件：HomeMainFragment.kt
 * 作者：Hujm
 * 添加版本：V3.0.8
 * 时间：2021/5/27 0027 10:58
 */
public class HomeMainFragment : LibraryLazyFragment<FragmentHomeMainBinding,String>() {
    override fun getContentView(): Int {
        return R.layout.fragment_home_main
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