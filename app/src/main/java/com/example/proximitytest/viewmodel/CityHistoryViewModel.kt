package com.example.proximitytest.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.proximitytest.database.citydata.City
import com.example.proximitytest.database.RoomSingleton
import com.example.proximitytest.database.citydata.CityHistory
import com.example.proximitytest.repository.CityHistoryRepository
import com.example.proximitytest.repository.CityRepository

class CityHistoryViewModel(application: Application) : ViewModel() {

    private val cityRepository: CityHistoryRepository
    lateinit var cityHistory: LiveData<List<CityHistory>>

    init {
        cityRepository = CityHistoryRepository(RoomSingleton.getInstance(application).getCityHistoryDao())
    }

    fun getAllcityData(name: String): LiveData<List<CityHistory>> {
        cityHistory = cityRepository.getCityHistoryData(name)
        return cityHistory;
    }

}