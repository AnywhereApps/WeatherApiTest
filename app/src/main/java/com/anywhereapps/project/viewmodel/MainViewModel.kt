package com.anywhereapps.project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anywhereapps.project.network.data.WeatherReport
import com.anywhereapps.project.repo.IRepository
import com.anywhereapps.project.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: IRepository
) : ViewModel() {

    val EXCULDE_VALUES: String = "daily,minutely"
    val APP_ID: String = "b05a3577e844d86544879d15814bd5a1"
    var latitude : String? = null
    var longitude : String? = null
    val report: MutableLiveData<Status<WeatherReport>> = MutableLiveData()

    fun fetchWeather(){
        report.postValue(Status.Loading())
        viewModelScope.launch {
            try {
                latitude?.let {
                    longitude?.let { it2->
                        val response = repository.getWeatherReport(it, it2, EXCULDE_VALUES, APP_ID)
                        report.postValue(Status.Success(response))
                    }
                }
            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> report.postValue(Status.Error("Network Failure " +  ex.localizedMessage))
                    else -> report.postValue(Status.Error("Something went wrong"))
                }
            }
        }
    }


}