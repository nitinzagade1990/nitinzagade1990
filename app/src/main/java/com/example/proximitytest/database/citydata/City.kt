package com.example.proximitytest.database.citydata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.proximitytest.constants.DbConstants
import com.example.proximitytest.database.converter.DateConverter
import com.google.gson.annotations.SerializedName
import java.time.Instant
import java.time.OffsetDateTime
import java.util.*

@Entity(tableName = DbConstants.TABLE_NAME_CITY)
@TypeConverters(DateConverter::class)
data class City(
    @PrimaryKey val city:
    String,
    val aqi: Float,

    var lastModified: Long,
    var textColo: Int


)


