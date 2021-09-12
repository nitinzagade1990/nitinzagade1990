package com.example.proximitytest.repository

import androidx.lifecycle.LiveData
import androidx.room.Insert
import com.example.proximitytest.database.citydata.City
import com.example.proximitytest.database.citydata.CityHistory
import com.example.proximitytest.database.dao.CityDao
import com.example.proximitytest.database.dao.CityHistoryDao

class CityHistoryRepository(var cityDao: CityHistoryDao) {

    fun getCityHistoryData(citYName: String): LiveData<List<CityHistory>> {
        return cityDao.getCityHistoryData(citYName)
    }

    fun insertCityHistoryData(city: CityHistory) {
        cityDao.insert(city)
    }

    fun getCityHistory(cityName: String): CityHistory {
        return cityDao.getCityHistory(cityName)
    }


}