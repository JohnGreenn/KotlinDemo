package com.example.bean;

import com.zhouyou.http.model.ApiResult;

/**
 * 描述：
 * fileName：com.example.bean
 * author：Hujm
 * 添加版本：V4.2.12
 * time：2021/05/18 17:50
 */
public class BaseResult<T> extends ApiResult<T> {
    private String info;

    @Override
    public String getMsg() {
        return info;
    }

    @Override
    public void setMsg(String info) {
        this.info = info;
    }

    @Override
    public boolean isOk() {
        return getCode() == 200;//如果不是0表示成功，请重写isOk()方法。
//        return true;//如果不是0表示成功，请重写isOk()方法。
    }
}
