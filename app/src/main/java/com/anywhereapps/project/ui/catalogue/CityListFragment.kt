package com.anywhereapps.project.ui.catalogue

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anywhereapps.project.R
import com.anywhereapps.project.databinding.FragmentCityListBinding
import com.anywhereapps.project.network.Item
import com.anywhereapps.project.viewmodel.CityListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CityListFragment : Fragment(R.layout.fragment_city_list), ItemsRVAdapter.OnItemClickedListener {

    private lateinit var binding : FragmentCityListBinding
    private lateinit var itemAdapter : ItemsRVAdapter
    private val cityListViewModel: CityListViewModel by viewModels()
    var datalist = arrayListOf<Item>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCityListBinding.bind(view)
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        itemAdapter = ItemsRVAdapter(this, context)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = itemAdapter
        setupSwipeToDelete(binding.recyclerView, itemAdapter)

        cityListViewModel.getCityLists()
        observeLiveData()
        setClickListeners()
    }

    private fun observeLiveData() {
        cityListViewModel.items.observe(viewLifecycleOwner) {
            datalist.addAll(it)
            itemAdapter.submitData(datalist)
        }
    }

    private fun setClickListeners(){
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.addCityButton.setOnClickListener {
            findNavController().navigate(R.id.action_to_add_city_page)
        }
    }


    override fun onItemClicked(item: Item) {
//        val bundle = bundleOf("item" to item)
//         findNavController().navigate(R.id.action_list_to_detail_page, bundle)
    }

    private fun setupSwipeToDelete(recyclerview : RecyclerView,
                                   adapter : RecyclerView.Adapter<ItemsRVAdapter.ItemViewHolder>){
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               var item = (adapter as? ItemsRVAdapter)?.items?.get(viewHolder.adapterPosition)
                cityListViewModel.deleteCity(item?.id)
                datalist.removeAt(viewHolder.adapterPosition)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                Toast.makeText(context, getString(R.string.delete_city, item?.name), Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerview)
    }
}