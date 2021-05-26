package com.example.myapplication.activity

import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, String>() {

    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        super.initView()
    }


    override fun getData(isShow: Boolean) {
        super.getData(false)
    }

}