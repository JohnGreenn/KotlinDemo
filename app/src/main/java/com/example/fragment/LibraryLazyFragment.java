package com.example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.TitleBar;
import com.trello.rxlifecycle2.components.support.RxFragment;

import io.reactivex.Observable;


/**
 * @author Hujm
 * @version V1.0
 * @Title: LazyFragment.java
 * @Package com.hp.gb.fragment
 * @Description: Fragement 懒加载
 * @date 2015年6月25日 下午2:58:06
 */
public abstract class LibraryLazyFragment<T,E> extends RxFragment {
    public T binding;
    protected Context mContext;
    public View mView;
    public ImmersionBar mImmersionBar;
    protected Observable<E> observable;

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    /**
     * 标志位，标志已经初始化完成
     */
    protected boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    protected boolean mHasLoadedOnce;

    /**
     * 标题栏对象
     */
    protected TitleBar mTitleBar;
    /** 状态栏沉浸 */


    /**
     * 得到根Fragment
     *
     * @return
     */
    public Fragment getRootFragment() {
        Fragment fragment = getParentFragment();
        while(fragment != null & fragment.getParentFragment() != null) {
            fragment = fragment.getParentFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        if(mView == null) {
            mView = getLayoutInflater().inflate(getContentView(),null,false);
            binding = (T) DataBindingUtil.bind(mView);
            initBundleData();
            initView();
        }
        isPrepared = true;
        lazyLoad();
        ViewGroup parent = (ViewGroup) mView.getParent();
        if(parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {

    }


    /**
     * 延迟加载 子类必须重写此方法
     */
    protected abstract void lazyLoad();

    /**
     * 获取显示view
     */
    protected abstract int getContentView();

    /**
     * 获取上一个界面传送过来的数据
     */
    protected abstract void initBundleData();

    /**
     * 初始化view
     */
    protected abstract void initView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isVisible = false;
        isPrepared = false;
        if(mImmersionBar != null) {
            //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
            ImmersionBar.destroy(this);
        }
        ImmersionBar.destroy(this);
    }

    public boolean onBackPressed() {
        return false;
    }

}