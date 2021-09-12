package com.example.proximitytest.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.proximitytest.database.citydata.City
import com.example.proximitytest.database.RoomSingleton
import com.example.proximitytest.repository.CityRepository

class CityViewModel(application: Application) : ViewModel() {

    private val cityRepository: CityRepository
    private val allCityData: LiveData<List<City>>

    init {
        cityRepository = CityRepository(RoomSingleton.getInstance(application).getCityDao())
        allCityData = cityRepository.getCityData()
    }

    fun getAllcityData(): LiveData<List<City>> {
        return allCityData
    }

}