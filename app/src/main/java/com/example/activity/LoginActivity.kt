package com.example.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.Toast
import com.example.R
import com.example.activity.HttpUtile.Companion.executeCusB
import com.example.bean.InfoBean
import com.example.databinding.ActivityLoginBinding
import com.example.util.ClickUtil
import com.pddstudio.preferences.encrypted.EncryptedPreferences
import com.tbruyelle.rxpermissions2.RxPermissions
import com.zhouyou.http.EasyHttp
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
        immersionBar.let {
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


    private fun userLogin() {
        val params = HttpParams()
        params.put("mobile", et_loginphone.text.toString())
        params.put("password", et_password.text.toString())
        observable = EasyHttp.post("/user/login/index")
            .params(params)
//            .addInterceptor(CustomSignInterceptor())
            .executeCusB(InfoBean::class.java);
        observable.subscribe(object : BaseSubscriber<InfoBean>() {
            override fun onError(e: ApiException) {
                Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
            }

            @SuppressLint("CheckResult")
            override fun onNext(infoBean: InfoBean) {
                val rxPermissions = RxPermissions(this@LoginActivity)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    rxPermissions.requestEach(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ).subscribe { t ->
                        when {
                            t.granted -> {
                                // 用户已经同意该权限
                                saveUserInfo(infoBean)
                            }
                            t.shouldShowRequestPermissionRationale -> {
                                // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框

                            }
                            else -> {
                                // 用户拒绝了该权限，并且选中『不再询问』

                            }
                        }
                    }
                } else {
                    saveUserInfo(infoBean)
                }
            }
        })
    }

    /**
     * 描述：保存数据并跳转到首页
     * 作者：Hujm
     * 添加版本：V3.0.8
     * 时间：2021/5/31 0031 10:55
     * -------------------------------
     *
     */
    fun saveUserInfo(infoBean: InfoBean) {
        val encryptedPreferences: EncryptedPreferences =
            EncryptedPreferences.Builder(this).withEncryptionPassword("password")
                .build()
        encryptedPreferences.edit().putString("token", infoBean.token).apply()
        infoBean.let {
            startActivity(
                Intent(this@LoginActivity, MainActivity::class.java)
            )
            finish()
        }
    }
}

