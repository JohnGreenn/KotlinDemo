package com.example.myapplication.adapter;

import android.util.SparseArray;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 描述：dataBinding SuperViewHolder
 * 文件名：bindingSuperViewHolder.java
 * 作者：Hujm
 * 时间：2018/1/18 0018 11:35
 */
public class bindingSuperViewHolder extends RecyclerView.ViewHolder {
    public ViewDataBinding binding;
    private SparseArray<View> views;

    public bindingSuperViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.views = new SparseArray<>();
        this.binding = binding;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if(view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T) view;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    public void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }
}
