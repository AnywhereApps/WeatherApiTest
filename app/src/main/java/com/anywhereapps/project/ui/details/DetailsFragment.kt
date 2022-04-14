package com.anywhereapps.project.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.anywhereapps.project.R
import com.anywhereapps.project.databinding.FragmentDetailsBinding
import com.anywhereapps.project.network.Item
import com.anywhereapps.project.util.AppUtil
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailsBinding.bind(view)
        setToolbar(binding)
        /*     val item = arguments?.getParcelable("item") as? Item

           item?.let {
             binding.titleText.text = item.title
               binding.addressText.text = "${item.locationline1} , ${item.locationline2}"
               binding.detailText.text = item.description
               item.date?.let { binding.dateText.text = AppUtil.getTime(it) }
               Glide.with(this).load(item.image).into(binding.backdrop)
        }*/
    }


    private fun setToolbar(binding : FragmentDetailsBinding){
        binding.toolbar.title = ""
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
        }
    }

}