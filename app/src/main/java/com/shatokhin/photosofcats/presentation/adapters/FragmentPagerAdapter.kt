package com.shatokhin.photosofcats.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.shatokhin.photosofcats.presentation.fragments.FavoriteImagesCatsFragment
import com.shatokhin.photosofcats.presentation.fragments.VoteImagesCatsFragment

class FragmentPagerAdapter(fr: FragmentManager, lf: Lifecycle) :
    FragmentStateAdapter(fr, lf) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> VoteImagesCatsFragment()
            else -> FavoriteImagesCatsFragment()
        }

    }

}