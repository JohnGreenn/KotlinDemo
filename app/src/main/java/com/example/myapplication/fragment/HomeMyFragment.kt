package com.example.myapplication.fragment

import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeMyBinding

/**
 * 描述：首页-首页Fragment
 * 文件：HomeMainFragment.kt
 * 作者：Hujm
 * 添加版本：V3.0.8
 * 时间：2021/5/27 0027 10:58
 */
public class HomeMyFragment : LibraryLazyFragment<FragmentHomeMyBinding>() {
    override fun getContentView(): Int {
        return R.layout.fragment_home_my
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