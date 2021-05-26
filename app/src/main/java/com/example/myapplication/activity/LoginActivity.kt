package com.example.myapplication.activity

import android.content.Intent
import android.view.View
import android.widget.Switch
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

/**
 * 描述：
 * 文件：LoginActivity.kt
 * 作者：Hujm
 * 添加版本：V3.0.8
 * 时间：2021/5/26 0026 16:53
 */

class LoginActivity : BaseActivity<ActivityLoginBinding, InfoBean>() {
    override fun getContentViewId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        /*设置状态栏*/
        immersionBar?.let {
            immersionBar.statusBarColor(R.color.transparent)
            immersionBar.fitsSystemWindows(false)
            //状态栏字体是深色，不写默认为亮色
            //状态栏字体是深色，不写默认为亮色
            immersionBar.statusBarDarkFont(false)
            initImmersionBar()
        }

        binding.activity = this
        binding.click = ClickUtil
        binding.tvLoginBu.setOnClickListener { getData() }
    }


    /**
     * 描述：按钮事件
     * 作者：Hujm
     * 添加版本：V3.0.8
     * 时间：2021/5/26 0026 16:59
     * -------------------------------
     *
     */
    open fun onClick(view: View) {
        when (view.id) {
            /*登录按钮*/
            R.id.tv_login_bu -> userLogin()
            /*显示验证码登录*/
            R.id.tv_checkcodelogin -> {
                binding.type = 1
            }
            R.id.tv_pswlogin -> {
                binding.type = 0
            }
        }
    }

    fun userLogin() {
        val params = HttpParams()
        params.put("mobile", et_loginphone.text.toString())
        params.put("password", et_password.text.toString())
        observable = EasyHttp.post("/user/login/index")
            .params(params)
            .execute(object :
                CallClazzProxy<BaseResult<InfoBean>, InfoBean>(InfoBean::class.java) {});
        observable.subscribe(object : BaseSubscriber<InfoBean>() {
            override fun onError(e: ApiException) {
                Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
            }

            override fun onNext(infoBean: InfoBean) {
                infoBean?.let {
                    startActivity(
                        Intent(this@LoginActivity, MainActivity::class.java)
                    )
                    finish()
                }
            }
        })
    }
}

