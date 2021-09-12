package com.example.proximitytest.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.proximitytest.database.citydata.City
import com.example.proximitytest.database.citydata.CityHistory
import com.example.proximitytest.database.dao.CityDao
import com.example.proximitytest.database.dao.CityHistoryDao


@Database(entities = [City::class, CityHistory::class], version = 1)
abstract class RoomSingleton : RoomDatabase() {
    abstract fun getCityDao(): CityDao
    abstract fun getCityHistoryDao(): CityHistoryDao

    companion object {
        private var INSTANCE: RoomSingleton? = null
        fun getInstance(context: Context): RoomSingleton {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, RoomSingleton::class.java, "roomdb")
                    .allowMainThreadQueries().build()
            }
            return INSTANCE as RoomSingleton
        }
    }
}