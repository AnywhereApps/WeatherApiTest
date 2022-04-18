package com.anywhereapps.project.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.anywhereapps.project.R
import com.anywhereapps.project.databinding.FragmentForcastBinding
import com.anywhereapps.project.network.Item
import com.anywhereapps.project.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ForcastFragment : Fragment(R.layout.fragment_forcast) {

    private lateinit var binding: FragmentForcastBinding
    private lateinit var weatherFragmentAdapter: WeatherFragmentAdapter
    private val model: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForcastBinding.bind(view)

        checkInput()
        weatherFragmentAdapter = WeatherFragmentAdapter(this)
        binding.pager.adapter = weatherFragmentAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            model.fetchWeather()
            binding.swipeRefreshLayout.isRefreshing = false
        }


        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.current)
                1 -> getString(R.string.hourly)
                else -> ""
            }
        }.attach()
    }

    private fun checkInput(){
        val item = arguments?.getParcelable("item") as? Item
        if (item != null) {
            setBackNavigationInToolbar()
            binding.toolbar.title = item.name
            model.latitude = item.lat
            model.longitude = item.lng
            model.fetchWeather()
        }else{
            setMenuInToolbar()
        }
    }

    private fun setMenuInToolbar(){
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

    private fun setBackNavigationInToolbar(){
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
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
