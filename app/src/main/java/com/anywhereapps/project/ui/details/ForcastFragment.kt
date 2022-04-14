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
        /*     val item = arguments?.getParcelable("item") as? Item

           item?.let {
             binding.titleText.text = item.title
               binding.addressText.text = "${item.locationline1} , ${item.locationline2}"
               binding.detailText.text = item.description
               item.date?.let { binding.dateText.text = AppUtil.getTime(it) }
               Glide.with(this).load(item.image).into(binding.backdrop)
        }*/

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = when (position) {
                0 -> "Current"
                1 -> "Hourly"
                else -> ""
            }
        }.attach()
    }


    private fun setToolbar(binding : FragmentForcastBinding){
        binding.toolbar.title = ""
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        // setup share icon
        binding.toolbar.inflateMenu(R.menu.main_menu)
/*        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.shareButton -> {
                    AppUtil.shareContent(context,
                        binding.titleText.text.toString(),
                        binding.detailText.text.toString())
                    true
                }
                else -> false
            }
        }*/
    }





   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherFragmentAdapter = WeatherFragmentAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = weatherFragmentAdapter
    }*/
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
