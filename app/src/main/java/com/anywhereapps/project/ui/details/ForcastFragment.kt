package com.anywhereapps.project.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.anywhereapps.project.R
import com.anywhereapps.project.databinding.FragmentDetailsBinding
import com.anywhereapps.project.databinding.FragmentForcastBinding
import com.anywhereapps.project.network.Item
import com.anywhereapps.project.util.AppUtil
import com.anywhereapps.project.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ForcastFragment : Fragment(R.layout.fragment_forcast) {

    private lateinit var weatherFragmentAdapter: WeatherFragmentAdapter
    private val model: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentForcastBinding.bind(view)
        setToolbar(binding)

        weatherFragmentAdapter = WeatherFragmentAdapter(this)
        binding.pager.adapter = weatherFragmentAdapter


        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = when (position) {
                0 -> "Current"
                1 -> "Hourly"
                else -> ""
            }
        }.attach()
    }


    private fun setToolbar(binding : FragmentForcastBinding){
        binding.toolbar.title = getString(R.string.app_name)
        binding.toolbar.inflateMenu(R.menu.main_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.citiesButton -> {
                    findNavController().navigate(R.id.action_to_cities_page)
                    true
                }
                else -> false
            }
        }
    }

}

class WeatherFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
       return when (position) {
            0 -> CurrentFragment()
            1 -> HourlyFragment()
            else -> CurrentFragment()
        }
    }

}
