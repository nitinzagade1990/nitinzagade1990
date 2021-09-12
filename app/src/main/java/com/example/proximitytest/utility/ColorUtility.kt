package com.example.proximitytest.utility

import android.graphics.Color

class ColorUtility {

    companion object {

        fun getColor(aqi: Float): Int {

            val parsedColor = when (aqi) {

                in 0f..50f -> { Color.parseColor("#20aa56") }
                in 51f..100f -> { Color.parseColor("#99c959") }
                in 100f..200f -> { Color.parseColor("#fff83b") }
                in 200f..300f -> { Color.parseColor("#ff9825") }
                in 300f..400f -> { Color.parseColor("#ff2914") }
                in 400f..500f -> { Color.parseColor("#c21c0d") }
                else -> { Color.parseColor("#c21c0d") }
            }

            return parsedColor;
        }

    }
}