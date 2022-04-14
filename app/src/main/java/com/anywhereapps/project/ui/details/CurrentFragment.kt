package com.anywhereapps.project.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.anywhereapps.project.R
import com.anywhereapps.project.databinding.FragmentCurrentBinding
import com.anywhereapps.project.databinding.FragmentDetailsBinding
import com.anywhereapps.project.databinding.FragmentHourlyBinding
import com.anywhereapps.project.network.Item
import com.anywhereapps.project.ui.MainActivity
import com.anywhereapps.project.util.AppUtil
import com.anywhereapps.project.util.Status
import com.anywhereapps.project.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CurrentFragment : Fragment(R.layout.fragment_current) {

    private val model: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentCurrentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCurrentBinding.bind(view)
        setToolbar(binding)
        observeLiveData()
        /*     val item = arguments?.getParcelable("item") as? Item

           item?.let {
             binding.titleText.text = item.title
               binding.addressText.text = "${item.locationline1} , ${item.locationline2}"
               binding.detailText.text = item.description
               item.date?.let { binding.dateText.text = AppUtil.getTime(it) }
               Glide.with(this).load(item.image).into(binding.backdrop)
        }*/
    }


    private fun setToolbar(binding : FragmentCurrentBinding){
       /* binding.toolbar.title = ""
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        // setup share icon
        binding.toolbar.inflateMenu(R.menu.main_menu)
        binding.toolbar.setOnMenuItemClickListener {
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

    private fun observeLiveData() {
        model.report.observe(viewLifecycleOwner) {
            when (it) {
                is Status.Success -> {
                    (activity as? MainActivity)?.showProgressBar(false)
                    it.data?.let { it1 ->
                       binding.dateText.text = getString(R.string.date, it1.current?.dt)
                        binding.suriseText.text = getString(R.string.sunrise, it1.current?.sunrise)
                        binding.sunsetText.text = getString(R.string.sunset, it1.current?.sunset)
                        binding.tempText.text = getString(R.string.date, it1.current?.temp.toString())
                        binding.pressureText.text = getString(R.string.pressure, it1.current?.pressure.toString())
                        binding.humidityText.text = getString(R.string.humidity, it1.current?.humidity.toString())
                        binding.windspeedText.text = getString(R.string.windspeed, it1.current?.windSpeed.toString())
                    }

                }
                is Status.Error -> {
                    it.message?.let { message ->
                        (activity as? MainActivity)?.showProgressBar(false)
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }
                is Status.Loading -> {
                    (activity as? MainActivity)?.showProgressBar(true)
                }
            }

        }
    }

}