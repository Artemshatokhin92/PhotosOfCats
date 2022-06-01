package com.shatokhin.photosofcats

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shatokhin.photosofcats.databinding.ActivityMainBinding
import com.shatokhin.photosofcats.presentation.adapters.FragmentPagerAdapter
import com.shatokhin.photosofcats.presentation.viewmodels.ViewModelMain
import com.shatokhin.photosofcats.presentation.viewmodels.ViewModelMainFactory


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModelMain: ViewModelMain by viewModels { ViewModelMainFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()

    }

    private fun initViewPager() {
        val pagerAdapter = FragmentPagerAdapter( supportFragmentManager, lifecycle )

        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = pagerAdapter

        // создание TabLayout (view с вкладками) который привязывается к viewPager
        val tabLayout: TabLayout = binding.tabs
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position){
                0 -> tab.text = "Votes"
                1 -> tab.text = "Favorites"
            }
        }.attach()


    }

}