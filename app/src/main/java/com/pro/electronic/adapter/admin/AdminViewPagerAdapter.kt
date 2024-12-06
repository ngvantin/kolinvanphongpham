package com.pro.electronic.adapter.admin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pro.electronic.fragment.admin.AdminCategoryFragment
import com.pro.electronic.fragment.admin.AdminOrderFragment
import com.pro.electronic.fragment.admin.AdminProductFragment
import com.pro.electronic.fragment.admin.AdminSettingsFragment

class AdminViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> AdminProductFragment()

            2 -> AdminOrderFragment()

            3 -> AdminSettingsFragment()

            else -> AdminCategoryFragment()
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}
