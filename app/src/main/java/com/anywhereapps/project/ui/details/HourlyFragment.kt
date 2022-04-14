package com.anywhereapps.project.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.anywhereapps.project.R
import com.anywhereapps.project.databinding.FragmentCatalogueBinding
import com.anywhereapps.project.databinding.FragmentDetailsBinding
import com.anywhereapps.project.databinding.FragmentHourlyBinding
import com.anywhereapps.project.network.Item
import com.anywhereapps.project.ui.MainActivity
import com.anywhereapps.project.ui.catalogue.ItemsRVAdapter
import com.anywhereapps.project.util.AppUtil
import com.anywhereapps.project.util.Status
import com.anywhereapps.project.viewmodel.CatalogueViewModel
import com.anywhereapps.project.viewmodel.MainViewModel
import com.example.example.Hourly
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HourlyFragment : Fragment(R.layout.fragment_hourly) , HourlyAdapter.OnItemClickedListener{
    private val model: MainViewModel by activityViewModels()
    private lateinit var itemAdapter : HourlyAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHourlyBinding.bind(view)

        itemAdapter = HourlyAdapter(this, context)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = itemAdapter

        observeLiveData()
    }

    private fun observeLiveData() {
        model.report.observe(viewLifecycleOwner) {
            when (it) {
                is Status.Success -> {
                    it.data?.let { it1 -> itemAdapter.submitData(it1.hourly)  }
                }
                is Status.Error -> {
                    it.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }
                is Status.Loading -> {
                }
            }

        }
    }

    override fun onItemClicked(item: Hourly) {

    }

}