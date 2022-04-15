package com.anywhereapps.project.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.anywhereapps.project.R
import com.anywhereapps.project.databinding.ActivityMainBinding
import com.anywhereapps.project.viewmodel.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val model: MainViewModel by viewModels()

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = ActivityMainBinding.inflate(layoutInflater)
       setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStart() {
        super.onStart()
        checkPermission()
    }

    fun showProgressBar(enable : Boolean){
        binding?.progressCircular.visibility = if(enable) View.VISIBLE else View.GONE
    }

    private fun checkPermission(){
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    getLastLocation()
                } else -> {
                val snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.pressure),
                    LENGTH_INDEFINITE)
                snackbar.show()
            }
            }
        }
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION))
    }


    @SuppressLint("MissingPermission")
    fun getLastLocation(){
        fusedLocationClient.lastLocation
            .addOnSuccessListener {
                it?.let {
                    model.fetchWeather(it.latitude.toString(), it.longitude.toString())
                }
            }
    }

}