package com.pro.electronic.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pro.electronic.fragment.ProductFragment
import com.pro.electronic.model.Category

class CategoryPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val listCategory: List<Category>?
) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return ProductFragment.newInstance(listCategory!![position].id)
    }

    override fun getItemCount(): Int {
        if (listCategory != null) return listCategory.size
        return 0
    }
}
