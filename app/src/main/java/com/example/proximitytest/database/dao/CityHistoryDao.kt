package com.example.proximitytest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proximitytest.database.citydata.CityHistory

@Dao
interface CityHistoryDao {


    @Insert
    fun insert(city: CityHistory)

    @Query("SELECT * FROM city_history where city=:cityName ORDER BY lastModified DESC LIMIT 1")
    fun getCityHistory(cityName: String): CityHistory

    @Query("SELECT * FROM city_history where city=:cityName ")
    fun getCityHistoryData(cityName: String): LiveData<List<CityHistory>>

}