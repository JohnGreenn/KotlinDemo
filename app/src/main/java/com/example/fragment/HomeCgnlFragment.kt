package com.example.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.R
import com.example.activity.HttpUtile.Companion.executeList
import com.example.adapter.bindingSuperViewHolder
import com.example.bean.CgnlBean
import com.example.databinding.FragmentHomeCgnlBinding
import com.example.databinding.LdfaItemXmlBinding
import com.github.jdsjlzx.ItemDecoration.DividerDecoration
import com.google.gson.reflect.TypeToken
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.exception.ApiException
import com.zhouyou.http.model.HttpParams
import com.zhouyou.http.subsciber.BaseSubscriber
import kotlinx.android.synthetic.main.fragment_home_cgnl.*


/**
 * 描述：首页-成功案例Fragment
 * 文件：HomeMainFragment.kt
 * 作者：Hujm
 * 添加版本：V3.0.8
 * 时间：2021/5/27 0027 10:58
 */
 class HomeCgnlFragment :
    RecyclerViewFragment<FragmentHomeCgnlBinding, ArrayList<CgnlBean>, CgnlBean>() {


    override fun getContentView(): Int {
        return R.layout.fragment_home_cgnl
    }

    override fun lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return
        }
        getData(false)
//        mHasLoadedOnce = true
    }

    override fun initBundleData() {
    }

    override fun initView() {
        binding.fragment = this
        initRecyclerView(binding.lvGoodslist, true)
        binding.lvGoodslist.addItemDecoration(
            DividerDecoration.Builder(activity).setHeight(R.dimen.dp_5)
                .setColorResource(R.color.transparent).build()
        )
    }


    override fun getData(isShow: Boolean) {
        val params = HttpParams()
        params.put("category_id", "0")
        params.put("hot", "desc")
        params.put("page", "1")
        params.put("pageSize", "20")
        observable = EasyHttp.get("/api/landing_plan/index")
            .params(params)
            .executeList(object : TypeToken<ArrayList<CgnlBean>>() {}.type)

        observable.subscribe(object : BaseSubscriber<ArrayList<CgnlBean>>() {
            override fun onError(e: ApiException) {
                Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
            }

            override fun onNext(value: ArrayList<CgnlBean>) {
//                Toast.makeText(activity, data.toString(), Toast.LENGTH_LONG).show()
//                if (page === 1) {
                oneAdapter.setDataList(value)
                binding.lvGoodslist.setNoMore(true)
//                } else {
//                    if (page === 1 && value.getBaseData() != null) {
//                        oneAdapter.setDataList(value.getBaseData())
//                    } else {
//                        if (value.getBaseData() == null || value.getBaseData().size() === 0) {
//                            binding.lvGoodslist.setNoMore(true)
//                        } else {
//                            oneAdapter.addAll(value.getBaseData())
//                        }
//                    }
//                }
                oneRecyclerView.refreshComplete(value?.size ?: 0)
                onemLRecyclerViewAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onCreateViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): bindingSuperViewHolder {
        return bindingSuperViewHolder(
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.ldfa_item_xml,
                parent,
                false
            )
        )
    }

    override fun onBindItemHolder(
        listData: MutableList<CgnlBean>,
        holder: bindingSuperViewHolder,
        position: Int
    ) {
        if (holder.getBinding() is LdfaItemXmlBinding) {
            (holder.getBinding() as LdfaItemXmlBinding).data = listData[position]
//            (holder.getBinding() as LdfaItemXmlBinding).setClick(ClickUtil())
            holder.getBinding().executePendingBindings()
        }

    }

}