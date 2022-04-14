package com.anywhereapps.project.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anywhereapps.project.databinding.ActivityMainBinding
import com.anywhereapps.project.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = ActivityMainBinding.inflate(layoutInflater)
       setContentView(binding.root)

        model.fetchWeather()
    }

    fun showProgressBar(enable : Boolean){
        binding?.progressCircular.visibility = if(enable) View.VISIBLE else View.GONE
    }

}