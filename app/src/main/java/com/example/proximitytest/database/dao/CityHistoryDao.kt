package com.example.proximitytest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.proximitytest.database.City

@Dao
interface CityDao {
//    @Insert
//    fun insert(city: City)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: List<City>)

    @Query("SELECT * FROM city_table  ")
    fun findByTitle(): LiveData<List<City>>

}