package com.example.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * 描述：
 * fileName：com.maxtv.maiyulive.view
 * author：Hujm
 * 添加版本：V4.2.12
 * time：2019/08/27 9:25
 */
public class MytextView extends AppCompatTextView {
    public MytextView(Context context) {
        super(context);
        notlb();
    }

    public MytextView(Context context,AttributeSet attrs) {
        super(context,attrs);
        notlb();
    }

    public MytextView(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        notlb();
    }

    private void notlb() {
        setIncludeFontPadding(false);
    }
}
