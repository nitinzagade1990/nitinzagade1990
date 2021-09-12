package com.example.proximitytest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.proximitytest.database.citydata.City

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: List<City>)

    @Query("SELECT * FROM city_table  ORDER BY city ASC")
    fun findByTitle(): LiveData<List<City>>


}