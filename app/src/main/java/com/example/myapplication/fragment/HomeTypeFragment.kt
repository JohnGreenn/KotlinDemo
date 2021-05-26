package com.example.myapplication.fragment

import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeTypeBinding

/**
 * 描述：首页-分类Fragment
 * 文件：HomeTypeFragment.kt
 * 作者：Hujm
 * 添加版本：V3.0.8
 * 时间：2021/5/27 0027 10:58
 */
public class HomeTypeFragment : LibraryLazyFragment<FragmentHomeTypeBinding>() {
    override fun getContentView(): Int {
        return R.layout.fragment_home_type
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