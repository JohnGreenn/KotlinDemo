package com.example.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.adapter.BindingBaseAdapter;
import com.example.adapter.IBindingBaseAdapter;
import com.example.adapter.LayoutManagerType;
import com.example.adapter.bindingSuperViewHolder;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.List;

import io.reactivex.Observable;

/**
 * 基类Fragment
 * 备注:所有的Fragment都继承自此Fragment
 * 1.规范团队开发
 * 2.统一处理Fragment所需配置,初始化
 *
 * @author ZhongDaFeng
 */
public abstract class RecyclerViewFragment<T,B,E> extends LibraryLazyFragment<T,E> implements IBindingBaseAdapter<E> {
    /*RecyclerView-----start------*/
    public LRecyclerView oneRecyclerView;

    /*调用字段*/
    protected Observable<B> observable;
    public BindingBaseAdapter<E> oneAdapter = null;
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
    protected void initRecyclerView(LRecyclerView recyclerView,boolean isBotMore) {
        this.oneRecyclerView = recyclerView;
        layoutManagerType = LayoutManagerType.Linear;
        oneRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initRecyclerViewSecond(isBotMore);
    }


    /**
     * @param recyclerView
     * @param emptyView
     * @param isBotMore
     * @description:添加无数据空白控件
     * @return: void
     * @author: sgp
     * @time: 2020/11/11 09:22
     * Version:3.0.9
     */
    protected void initRecyclerView(LRecyclerView recyclerView,View emptyView,boolean isBotMore) {
        this.oneRecyclerView = recyclerView;
        layoutManagerType = LayoutManagerType.Linear;
        oneRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //oneRecyclerView.setEmptyView(emptyView);
        initRecyclerViewSecond(isBotMore);
    }


    /**
     * 描述：
     * 作者：Hujm
     * 添加版本：V3.0.8
     * 时间：2021/4/16 11:17
     * -------------------------------
     *
     * @param recyclerView
     * @param layoutManagerType
     * @param gridCount         一行显示个数
     * @param isBotMore         是否开启加载更多
     */
    protected void initRecyclerView(LRecyclerView recyclerView,LayoutManagerType layoutManagerType,int gridCount,boolean isBotMore) {
        this.oneRecyclerView = recyclerView;
        this.layoutManagerType = layoutManagerType;
        if(layoutManagerType == LayoutManagerType.Linear) {
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            oneRecyclerView.setLayoutManager(manager);
        } else {
            GridLayoutManager manager = new GridLayoutManager(getActivity(),gridCount);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (onemLRecyclerViewAdapter.isHeader(position) || onemLRecyclerViewAdapter.isFooter(position) || onemLRecyclerViewAdapter.isRefreshHeader(position))
                            ? manager.getSpanCount() : 1;
                }
            });
            oneRecyclerView.setLayoutManager(manager);
        }
        initRecyclerViewSecond(isBotMore);
    }


    private void initRecyclerViewSecond(boolean isBotMore) {
        oneAdapter = new BindingBaseAdapter<E>(getActivity()) {
            @Override
            public int getLayoutId() {
                return RecyclerViewFragment.this.getLayoutId();
            }

            @Override
            public void onBindItemHolder(bindingSuperViewHolder holder,int position) {
                RecyclerViewFragment.this.onBindItemHolder(listData,holder,position);

            }

            @Override
            public int getItemViewType(int position) {
                return RecyclerViewFragment.this.getItemViewType(listData,position);
            }

            @Override
            public bindingSuperViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
                return RecyclerViewFragment.this.onCreateViewHolder(layoutInflater,parent,viewType);
            }


            @Override
            public void onBindItemHolder(bindingSuperViewHolder holder,int position,List<Object> payloads) {
                RecyclerViewFragment.this.onBindItemHolder(holder,position,payloads);
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
            if(page == 1 && onemLRecyclerViewAdapter.getItemCount() < size) {
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
    public int getItemViewType(List<E> listData,int position) {
        return 0;
    }

    @Override
    public void onBindItemHolder(bindingSuperViewHolder holder,int position,List<Object> payloads) {
    }

    @Override
    public void onBindItemHolder(List<E> listData,bindingSuperViewHolder holder,int position) {
    }

    @Override
    public bindingSuperViewHolder onCreateViewHolder(LayoutInflater layoutInflater,ViewGroup parent,int viewType) {
        if(getLayoutId() != 0) {
            return new bindingSuperViewHolder(DataBindingUtil.inflate(layoutInflater,getLayoutId(),parent,false));
        } else {
            return null;
        }
    }

    /*RecyclerView-----end------*/
    /*获取数据-----start------*/
    protected void getData(boolean isShow) {
    }
    /*获取数据-----end------*/
}
