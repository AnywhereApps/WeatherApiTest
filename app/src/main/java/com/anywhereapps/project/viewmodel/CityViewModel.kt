package com.anywhereapps.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anywhereapps.project.network.Item
import com.anywhereapps.project.repo.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CityViewModel @Inject constructor(
    private val repository: IRepository
) : ViewModel() {

    fun addCity(id : Int, name : String, lat : String , lng : String){
        viewModelScope.launch {
            try {
                 repository.saveCity(Item(id, name, lat, lng))
            } catch (ex: Exception) {
            }
        }
    }

}