package com.example.myapplication.activity

import android.view.View
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.bean.BaseResult
import com.example.myapplication.bean.InfoBean
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.util.ClickUtil
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.callback.CallClazzProxy
import com.zhouyou.http.exception.ApiException
import com.zhouyou.http.model.HttpParams
import com.zhouyou.http.subsciber.BaseSubscriber
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity<ActivityLoginBinding, InfoBean>() {
    override fun getContentViewId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        binding.activity = this
        binding.click = ClickUtil()
        binding.tvLoginBu.setOnClickListener { getData() }
    }

    fun getData() {
        val params = HttpParams()
        params.put("mobile", et_loginphone.text.toString())
        params.put("password", et_password.text.toString())
        observable = EasyHttp.post("/user/login/index")
            .params(params)
            .execute(object :
                CallClazzProxy<BaseResult<InfoBean>, InfoBean>(InfoBean::class.java) {});
        observable.subscribe(object : BaseSubscriber<InfoBean>() {
            override fun onError(e: ApiException) {
                Toast.makeText(this@LoginActivity, "123", Toast.LENGTH_LONG).show()
            }

            override fun onNext(infoBean: InfoBean) {
                Toast.makeText(this@LoginActivity, infoBean.nickname, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun onClick(view: View) {
        Toast.makeText(this, "111", Toast.LENGTH_LONG).show()
    }
}

