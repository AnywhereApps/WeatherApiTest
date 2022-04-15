package com.anywhereapps.project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anywhereapps.project.network.Item
import com.anywhereapps.project.repo.IRepository
import com.anywhereapps.project.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class CityListViewModel @Inject constructor(
    private val repository: IRepository
) : ViewModel() {

    val items: MutableLiveData<List<Item>> = MutableLiveData()

    fun getCityLists(){
        viewModelScope.launch {
            try {
                items.value = repository.getCities()
            } catch (ex: Exception) {

            }
        }
    }

    fun deleteCity(id : Int?) {
        viewModelScope.launch {
            id?.let {
                repository.deleteCity(it)
            }
        }
    }

}