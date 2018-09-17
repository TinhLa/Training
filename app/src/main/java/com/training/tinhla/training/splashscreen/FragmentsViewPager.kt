package com.training.tinhla.training.splashscreen

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import javax.inject.Inject

class FragmentsViewPager @Inject constructor(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    private var fragments:ArrayList<Fragment> = ArrayList()

    fun add(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun getItem(position: Int): Fragment? {
        if(position < 0 || position >= fragments.size)
            return null
        return fragments.get(position)
    }

    override fun getCount(): Int {
        return fragments.size
    }
}