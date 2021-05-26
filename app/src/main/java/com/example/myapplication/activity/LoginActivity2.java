package com.example.myapplication.activity;

import android.view.View;
import android.widget.Toast;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.R;
import com.example.myapplication.bean.BaseResult;
import com.example.myapplication.bean.InfoBean;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.CallClazzProxy;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.ApiResult;
import com.zhouyou.http.model.HttpParams;
import com.zhouyou.http.subsciber.BaseSubscriber;
import com.zhouyou.http.subsciber.CallBackSubsciber;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public class LoginActivity2 extends BaseActivity<ActivityLoginBinding,InfoBean> {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HttpParams params = new HttpParams();
//                params.put("mobile", "15056932970");
//                params.put("password", "123456");
//                Observable<InfoBean> observable = EasyHttp.get("/user/login/index")
//                        .timeStamp(true)
//                        .params(params)
////                        .execute(new CallBackProxy<BaseResult<InfoBean>, InfoBean>(new SimpleCallBack<InfoBean>() {
////                            @Override
////                            public void onError(ApiException e) {
////                                Toast.makeText(LoginActivity2.this, "11", Toast.LENGTH_LONG).show();
////                            }
////
////                            @Override
////                            public void onSuccess(InfoBean infoBean) {
////                            Toast.makeText(LoginActivity2.this, infoBean.getNickname(), Toast.LENGTH_LONG).show();
////                            }
////                        }) {
////                        });
//                        .execute(new SimpleCallBack<InfoBean>() {
//                            @Override
//                            public void onError(ApiException e) {
//                                Toast.makeText(LoginActivity2.this,"11", Toast.LENGTH_LONG).show();
//                            }
//
//                            @Override
//                            public void onSuccess(InfoBean infoBean) {
//                                Toast.makeText(LoginActivity2.this, infoBean.getNickname(), Toast.LENGTH_LONG).show();
//                            }
//                        });
//                        .execute(new CallClazzProxy<BaseResult<InfoBean>, InfoBean>(InfoBean.class) {
//
//                        });
//                observable.subscribe(new BaseSubscriber<InfoBean>() {
//                    @Override
//                    public void onError(ApiException e) {
//                        Toast.makeText(LoginActivity2.this, "123", Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onNext(@NotNull InfoBean infoBean) {
//                        Toast.makeText(LoginActivity2.this, infoBean.getNickname(), Toast.LENGTH_LONG).show();
//                    }
//                });
            }
        });
//
    }
}