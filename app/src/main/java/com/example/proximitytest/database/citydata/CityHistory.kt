package com.example.proximitytest.database.citydata

import androidx.room.*
import com.example.proximitytest.constants.DbConstants
import com.example.proximitytest.database.converter.DateConverter
import com.google.gson.annotations.SerializedName
import java.time.Instant
import java.time.OffsetDateTime
import java.util.*

@Entity(tableName = DbConstants.TABLE_NAME_CITY_HISTORY)

data class CityHistory(
    val city: String,
    val aqi: Float,
    var lastModified: Long,

    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}