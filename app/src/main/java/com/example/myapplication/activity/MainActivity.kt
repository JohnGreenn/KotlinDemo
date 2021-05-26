package com.example.myapplication.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.fragment.*

class MainActivity : BaseActivity<ActivityMainBinding, String>() {

    var titles = arrayOf("首页", "分类", "优惠券", "成功案例", "我的")
    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        /*设置状态栏*/
        immersionBar?.let {
            immersionBar.statusBarColor(R.color.transparent)
            immersionBar.fitsSystemWindows(false)
            //状态栏字体是深色，不写默认为亮色
            //状态栏字体是深色，不写默认为亮色
            immersionBar.statusBarDarkFont(false)
            initImmersionBar()
        }

        var fragments = arrayOf(
            HomeMainFragment(),
            HomeTypeFragment(),
            HomeYHQFragment(),
            HomeCgnlFragment(),
            HomeMyFragment()
        )
        binding.mainViewpage.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return fragments.size
            }

            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return titles.get(position)
            }
        }
        binding.bvHomeNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> binding.mainViewpage.currentItem = 0
                R.id.home_type -> binding.mainViewpage.currentItem = 1
                R.id.home_yhq -> binding.mainViewpage.currentItem = 2
                R.id.home_al -> binding.mainViewpage.currentItem = 3
                R.id.home_me -> binding.mainViewpage.currentItem = 4
//                else -> binding.mainViewpage.currentItem = 4
            }
            true
        }
        /*设置当前选中项*/
        binding.bvHomeNavigation.selectedItemId = R.id.menu_home
    }


    override fun getData(isShow: Boolean) {

    }

}