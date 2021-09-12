package com.example.proximitytest.repository

import androidx.lifecycle.LiveData
import com.example.proximitytest.database.citydata.City
import com.example.proximitytest.database.dao.CityDao

class CityRepository(var cityDao: CityDao) {


    fun getCityData(): LiveData<List<City>> {
        return cityDao.findByTitle()
    }

    fun insertCityData(city: List<City>) {
        return cityDao.insert(city)
    }


}