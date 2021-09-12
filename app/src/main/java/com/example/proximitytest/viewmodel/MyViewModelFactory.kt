package com.example.proximitytest.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proximitytest.repository.CityRepository

class MyViewModelFactory constructor(private val app: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java!!)) {
            return CityViewModel(app) as T
        } else if (modelClass.isAssignableFrom(CityHistoryViewModel::class.java!!)) {
            return CityHistoryViewModel(app) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}