package com.anywhereapps.project.ui.catalogue

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anywhereapps.project.R
import com.anywhereapps.project.databinding.FragmentAddCityBinding
import com.anywhereapps.project.network.Item
import com.anywhereapps.project.viewmodel.CityViewModel
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
 class AddCityFragment : Fragment(R.layout.fragment_add_city), ItemsRVAdapter.OnItemClickedListener {

//    private lateinit var itemAdapter : ItemsRVAdapter
    private val cityViewModel: CityViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAddCityBinding.bind(view)

        val apiKey = getString(R.string.place_key)

        if (!Places.isInitialized()) {
            Places.initialize(context, apiKey)
        }

        val placesClient = Places.createClient(context)


        val autocompleteFragment = childFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment
        autocompleteFragment.setTypeFilter(TypeFilter.CITIES)
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(error: com.google.android.gms.common.api.Status) {
                Log.i("TAG", "Error: ${error.statusMessage}, ")
            }

            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.
                Log.i("TAG", "Place: ${place.name}, ${place.id}")
            }

            /*override fun onError(status: Status) {
                // TODO: Handle the error.
              //  Log.i(TAG, "An error occurred: $status")
            }*/
        })



    }



    override fun onItemClicked(item: Item) {
//        val bundle = bundleOf("item" to item)
//         findNavController().navigate(R.id.action_list_to_detail_page, bundle)
    }
}