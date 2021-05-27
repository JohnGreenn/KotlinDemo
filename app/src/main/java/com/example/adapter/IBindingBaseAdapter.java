package com.example.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * 描述： 基础Adapter
 * 文件名：NewsDetailListAdapter.java
 * 文件：hujm
 * 作者：2017/1/11 23:15
 */
public interface IBindingBaseAdapter<T> {
    bindingSuperViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType);

    int getLayoutId();

    int getItemViewType(List<T> listData, int position);

    void onBindItemHolder(List<T> listData, bindingSuperViewHolder holder, int position);

    void onBindItemHolder(bindingSuperViewHolder holder, int position, List<Object> payloads);
}
