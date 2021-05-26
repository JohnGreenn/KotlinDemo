package com.example.myapplication.activity;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.adapter.BindingBaseAdapter;
import com.example.myapplication.adapter.IBindingBaseAdapter;
import com.example.myapplication.adapter.LayoutManagerType;
import com.example.myapplication.adapter.bindingSuperViewHolder;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.List;

public abstract class RecyclerViewActivity<T extends ViewDataBinding, E> extends BaseActivity<T, E> implements IBindingBaseAdapter<E> {
    /*RecyclerView-----start------*/
    public LRecyclerView oneRecyclerView;
    public BindingBaseAdapter oneAdapter = null;
    public LRecyclerViewAdapter onemLRecyclerViewAdapter = null;
    public int page = 1;
    public int size = 20;
    LayoutManagerType layoutManagerType = LayoutManagerType.Linear;

    /**
     * 描述：初始化 控件
     * 作者：Hujm
     * 添加版本：V3.12.13
     * 时间：2019/1/10 0010 15:46
     * -------------------------------
     *
     * @param recyclerView
     * @param isBotMore    是否加载更多
     */
    protected void initRecyclerView(LRecyclerView recyclerView, boolean isBotMore) {
        this.oneRecyclerView = recyclerView;
        layoutManagerType = LayoutManagerType.Linear;
        oneRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initRecyclerViewSecond(isBotMore);
    }

    protected void initRecyclerView(LRecyclerView recyclerView, LayoutManagerType layoutManagerType, int gridCount, boolean isBotMore) {
        this.oneRecyclerView = recyclerView;
        this.layoutManagerType = layoutManagerType;
        if (layoutManagerType == LayoutManagerType.Linear) {
            oneRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            oneRecyclerView.setLayoutManager(new GridLayoutManager(this, gridCount));
        }
        initRecyclerViewSecond(isBotMore);
    }

    private void initRecyclerViewSecond(boolean isBotMore) {
        oneAdapter = new BindingBaseAdapter<E>(this) {
            @Override
            public int getLayoutId() {
                return RecyclerViewActivity.this.getLayoutId();
            }

            @Override
            public int getItemViewType(int position) {
                return RecyclerViewActivity.this.getItemViewType(listData, position);
            }

            @Override
            public bindingSuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return RecyclerViewActivity.this.onCreateViewHolder(layoutInflater, parent, viewType);
            }

            @Override
            public void onBindItemHolder(bindingSuperViewHolder holder, int position) {
                RecyclerViewActivity.this.onBindItemHolder(listData, holder, position);
            }

            @Override
            public void onBindItemHolder(bindingSuperViewHolder holder, int position, List<Object> payloads) {
                RecyclerViewActivity.this.onBindItemHolder(holder, position, payloads);
            }
        };
        onemLRecyclerViewAdapter = new LRecyclerViewAdapter(oneAdapter);
        oneRecyclerView.setAdapter(onemLRecyclerViewAdapter);
        oneRecyclerView.setOnRefreshListener(() -> {
            page = 1;
            getData(false);
        });
        oneRecyclerView.setLoadMoreEnabled(isBotMore);
        oneRecyclerView.setOnLoadMoreListener(() -> {
            if (page == 1 && onemLRecyclerViewAdapter.getItemCount() < size) {
                oneRecyclerView.setLoadMoreEnabled(false);
            } else {
                page++;
                getData(false);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public int getItemViewType(List<E> listData, int position) {
        return 0;
    }

    @Override
    public void onBindItemHolder(bindingSuperViewHolder holder, int position, List<Object> payloads) {
    }

    @Override
    public void onBindItemHolder(List<E> listData, bindingSuperViewHolder holder, int position) {
    }

    @Override
    public bindingSuperViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        if (getLayoutId() != 0) {
            return new bindingSuperViewHolder(DataBindingUtil.inflate(layoutInflater, getLayoutId(), parent, false));
        } else {
            return null;
        }
    }
    /*RecyclerView-----end------*/
}
