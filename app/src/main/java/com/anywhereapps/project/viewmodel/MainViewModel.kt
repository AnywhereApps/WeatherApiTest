package com.anywhereapps.project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anywhereapps.project.network.Item
import com.anywhereapps.project.repo.IRepository
import com.anywhereapps.project.util.Status
import com.example.example.WeatherReport
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: IRepository
) : ViewModel() {

    val report: MutableLiveData<Status<WeatherReport>> = MutableLiveData()

    fun fetchWeather(){
        report.postValue(Status.Loading())
        viewModelScope.launch {
            try {
                val response = repository.getWeatherReport()
                report.postValue(Status.Success(response))
            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> report.postValue(Status.Error("Network Failure " +  ex.localizedMessage))
                    else -> report.postValue(Status.Error("Something went wrong"))
                }
            }
        }
    }


}