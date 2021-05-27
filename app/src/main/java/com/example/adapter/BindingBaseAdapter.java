package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 描述： 基础Adapter
 * 文件名：NewsDetailListAdapter.java
 * 文件：hujm
 * 作者：2017/1/11 23:15
 */
public abstract class BindingBaseAdapter<T> extends RecyclerView.Adapter<bindingSuperViewHolder> {
    public LayoutInflater layoutInflater;
    public Context context;
    public List<T> listData = new ArrayList<>();

    public BindingBaseAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public bindingSuperViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        return new bindingSuperViewHolder(DataBindingUtil.inflate(layoutInflater,getLayoutId(),parent,false));
    }

    @Override
    public void onBindViewHolder(bindingSuperViewHolder holder,int position) {
        onBindItemHolder(holder,position);
    }

    //局部刷新关键：带payload的这个onBindViewHolder方法必须实现
    @Override
    public void onBindViewHolder(bindingSuperViewHolder holder,int position,List<Object> payloads) {
        if(payloads.isEmpty()) {
            onBindViewHolder(holder,position);
        } else {
            onBindItemHolder(holder,position,payloads);
        }
    }

    public abstract int getLayoutId();

    public abstract void onBindItemHolder(bindingSuperViewHolder holder,int position);

    public void onBindItemHolder(bindingSuperViewHolder holder,int position,List<Object> payloads) {
    }

    @Override
    public int getItemCount() {
        if(listData != null && listData.size() > 0) {
            return listData.size();
        }
        return 0;
    }

    public List<T> getDataList() {
        return listData;
    }

    public void setDataList(Collection<T> list) {
        if(this.listData != null) {
            this.listData.clear();
        }
        if(null != list) {
            this.listData.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addAll(Collection<T> list) {
        if(list == null) {
            return;
        }
        int lastIndex = this.listData.size();
        if(this.listData != null) {
            this.listData.addAll(list);
            notifyItemRangeInserted(lastIndex,list.size());
        }
    }

    public void remove(int position) {
        this.listData.remove(position);
        notifyItemRemoved(position);
        // 如果移除的是最后一个，忽略
        if(position != (getDataList().size())) {
            notifyItemRangeChanged(position,this.listData.size() - position);
        }
    }

    public void reFresh(List<T> list) {
        this.listData = list;
        notifyDataSetChanged();
    }

    public void clear() {
        if(this.listData != null) {
            this.listData.clear();
            notifyDataSetChanged();
        }
    }
}