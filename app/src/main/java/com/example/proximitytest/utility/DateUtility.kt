package com.example.proximitytest.utility

import android.text.format.DateUtils
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DateUtility {

    companion object {

        fun getUpdatedTimeInWord(updatedTime: Long): String {

            var timeElapsed = Test.getTimeAgo(updatedTime)

            if (timeElapsed == null) {
                return "Just Now"
            }
            return timeElapsed
        }


        fun getDiffrenceInSeconds(dateOne: Long, dateTwo: Long): Long {

            try {
                var d1 = Date(dateOne)
                var d2 = Date(dateTwo)

                return TimeUnit.MILLISECONDS.toSeconds(d1.getTime() - d2.getTime());


            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return -1
        }


    }
}