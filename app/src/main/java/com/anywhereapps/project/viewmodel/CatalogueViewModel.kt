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
class CatalogueViewModel @Inject constructor(
    private val repository: IRepository
) : ViewModel() {

    val items: MutableLiveData<Status<List<Item>>> = MutableLiveData()

    fun fetchCatalogue(){
        items.postValue(Status.Loading())
        viewModelScope.launch {
            try {
                val response = repository.getWeatherReport()
             /*   if (response.isNotEmpty()) {
                    items.postValue(Status.Success(response))
                } else {
                    items.postValue(Status.Error("Empty "))
                }*/
            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> items.postValue(Status.Error("Network Failure " +  ex.localizedMessage))
                    else -> items.postValue(Status.Error("Something went wrong"))
                }
            }
        }
    }


}