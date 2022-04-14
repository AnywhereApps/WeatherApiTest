package com.anywhereapps.project.ui.catalogue

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anywhereapps.project.R
import com.anywhereapps.project.databinding.FragmentCatalogueBinding
import com.anywhereapps.project.network.Item
import com.anywhereapps.project.ui.MainActivity
import com.anywhereapps.project.util.Status
import com.anywhereapps.project.viewmodel.CatalogueViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogueFragment : Fragment(R.layout.fragment_catalogue), ItemsRVAdapter.OnItemClickedListener {

    private lateinit var itemAdapter : ItemsRVAdapter
    private val catalogueViewModel: CatalogueViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCatalogueBinding.bind(view)

        itemAdapter = ItemsRVAdapter(this, context)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = itemAdapter
        binding.toolbar.title = getString(R.string.app_name)

        catalogueViewModel.fetchCatalogue()
        observeLiveData()
    }

    private fun observeLiveData() {
        catalogueViewModel.items.observe(viewLifecycleOwner) {
            when (it) {
                is Status.Success -> {
                    (activity as? MainActivity)?.showProgressBar(false)
                    it.data?.let { it1 -> itemAdapter.submitData(it1)  }

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


    override fun onItemClicked(item: Item) {
        val bundle = bundleOf("item" to item)
         findNavController().navigate(R.id.action_list_to_detail_page, bundle)
    }
}