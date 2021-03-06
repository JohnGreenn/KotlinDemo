package com.example.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.R
import com.example.bean.BaseResult
import com.gyf.immersionbar.ImmersionBar
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.callback.CallClazzProxy
import com.zhouyou.http.request.GetRequest
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.lang.reflect.Type

/**
 * 描述：
 * fileName：com.example.activity
 * author：Hujm
 * 添加版本：V4.2.12
 * time：2021/05/18 15:05
 * E 实体类
 */
abstract class BaseActivity<T : ViewDataBinding, E> : AppCompatActivity() {
    protected lateinit var binding: T
    protected var mContext: Context? = null
    protected var mDisposable: Disposable? = null;
    protected lateinit var immersionBar: ImmersionBar

    /*调用字段*/
    protected lateinit var observable: Observable<E>
    protected var subscription: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<T>(this, getContentViewId())
        immersionBar =
            ImmersionBar.with(this) //            .transparentStatusBar()  //透明状态栏，不写默认透明色
                //            .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                //            .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
                .statusBarColor(R.color.white) //状态栏颜色，不写默认透明色
                //            .navigationBarColor(R.color.colorPrimary) //导航栏颜色，不写默认黑色
                //            .barColor(R.color.colorPrimary)  //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
                //                .statusBarAlpha(0.3f)  //状态栏透/明度，不写默认0.0f
                //            .navigationBarAlpha(0.4f)  //导航栏透明度，不写默认0.0F
                //            .barAlpha(0.3f)  //状态栏和导航栏透明度，不写默认0.0f
                .statusBarDarkFont(true) //状态栏字体是黑色，不写默认为白色
                //            .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
                //                .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
                //            .autoStatusBarDarkModeEnable(true,0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
                //            .autoNavigationBarDarkModeEnable(true,0.2f) //自动导航栏图标变色，必须指定导航栏颜色才可以自动变色哦
                //            .flymeOSStatusBarFontColor(R.color.white)  //修改flyme OS状态栏字体颜色
                //            .fullScreen(true)      //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
                //            .hideBar(BarHide.FLAG_HIDE_BAR)  //隐藏状态栏或导航栏或两者，不写默认不隐藏
                //            .addViewSupportTransformColor(toolbar)  //设置支持view变色，可以添加多个view，不指定颜色，默认和状态栏同色，还有两个重载方法
                //            .titleBar(view)    //解决状态栏和布局重叠问题，任选其一
                //            .titleBarMarginTop(view)     //解决状态栏和布局重叠问题，任选其一
                //            .statusBarView(view)  //解决状态栏和布局重叠问题，任选其一
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色，还有一些重载方法
        //            .supportActionBar(true) //支持ActionBar使用
        //            .statusBarColorTransform(R.color.black)  //状态栏变色后的颜色
        //            .navigationBarColorTransform(R.color.orange) //导航栏变色后的颜色
        //            .barColorTransform(R.color.orange)  //状态栏和导航栏变色后的颜色
        //            .removeSupportView(toolbar)  //移除指定view支持
        //            .removeSupportAllView() //移除全部view支持
        //            .navigationBarEnable(true)   //是否可以修改导航栏颜色，默认为true
        //            .navigationBarWithKitkatEnable(true)  //是否可以修改安卓4.4和emui3.x手机导航栏颜色，默认为true
        //            .navigationBarWithEMUI3Enable(true) //是否可以修改emui3.x手机导航栏颜色，默认为true
        //            .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
        //            .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
        //            .setOnKeyboardListener(new OnKeyboardListener() {    //软键盘监听回调，keyboardEnable为true才会回调此方法
        //                @Override
        //                public void onKeyboardChange(boolean isPopup,int keyboardHeight) {
        //                    LogUtils.e(isPopup);  //isPopup为true，软键盘弹出，为false，软键盘关闭
        //                }
        //            })
        //            .setOnNavigationBarListener(onNavigationBarListener) //导航栏显示隐藏监听，目前只支持华为和小米手机
        //            .setOnBarListener(OnBarListener) //第一次调用和横竖屏切换都会触发，可以用来做刘海屏遮挡布局控件的问题
        //            .addTag("tag")  //给以上设置的参数打标记
        //            .getTag("tag")  //根据tag获得沉浸式参数
        //            .reset() //重置所以沉浸式参数;
        //初始化沉浸式
        initImmersionBar()
        initView()
        getData()
    }

    /**
     * 初始化沉浸式
     */
    protected open fun initImmersionBar() {
        //设置共同沉浸式样式
        immersionBar!!.init()
    }

    /**
     * 获取显示view的xml文件ID
     */
    protected abstract fun getContentViewId(): Int

    /**
     * 获取上一个界面传送过来的数据
     */
    protected open fun initBundleData() {
    }

    /**
     * 初始化view
     */
    protected open fun initView() {}

    /*获取数据-----start------*/
    protected open fun getData(isShow: Boolean = false) {}


    override fun onDestroy() {
        super.onDestroy()
        /*取消网络请求*/
        subscription?.let { EasyHttp.cancelSubscription(subscription) }

    }

}