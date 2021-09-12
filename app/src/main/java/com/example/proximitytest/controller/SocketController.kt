package com.example.proximitytest.controller

import android.app.Application
import com.example.proximitytest.database.RoomSingleton
import com.example.proximitytest.database.citydata.City
import com.example.proximitytest.database.citydata.CityHistory
import com.example.proximitytest.repository.CityHistoryRepository
import com.example.proximitytest.repository.CityRepository
import com.example.proximitytest.utility.ColorUtility
import com.example.proximitytest.utility.DateUtility
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tech.gusavila92.websocketclient.WebSocketClient
import java.net.URI
import java.net.URISyntaxException

object SocketController {

    fun createWebSocketClient(application: Application) {

        var mDb = RoomSingleton.getInstance(application)
        var cityReposi = CityRepository(mDb.getCityDao())
        var cityHistoryRepo = CityHistoryRepository(mDb.getCityHistoryDao())

        val uri: URI
        try {
            uri = URI("ws://city-ws.herokuapp.com/")
        } catch (e: URISyntaxException) {
            e.printStackTrace()
            return
        }
        var webSocketClient = object : WebSocketClient(uri) {
            override fun onOpen() {
                println("onOpen")
                // webSocketClient.send("Hello, World!")

            }

            override fun onTextReceived(message: String) {
                //println("onTextReceived" + message)

                GlobalScope.launch(Dispatchers.IO) {

                    val list = Gson().fromJson(message, Array<City>::class.java).toMutableList()

                    list.map {
                        it.lastModified = System.currentTimeMillis()
                        it.textColo = ColorUtility.getColor(it.aqi)

                        cityReposi.insertCityData(list)

                        var cityHistory = cityHistoryRepo.getCityHistory(it.city)

                        if (cityHistory != null) {
                            var diffrenceInSeconds = DateUtility.getDiffrenceInSeconds(
                                it.lastModified,
                                cityHistory.lastModified
                            )


                            if (diffrenceInSeconds > 30) {
                                cityHistoryRepo.insertCityHistoryData(
                                    CityHistory(
                                        it.city,
                                        it.aqi,
                                        it.lastModified
                                    )
                                )
                            }

                        } else {
                            cityHistoryRepo.insertCityHistoryData(
                                CityHistory(
                                    it.city,
                                    it.aqi,
                                    it.lastModified
                                )
                            )
                        }

                    }
                }
            }

            override fun onBinaryReceived(data: ByteArray) {
                println("onBinaryReceived")
            }

            override fun onPingReceived(data: ByteArray) {
                println("onPingReceived")
            }

            override fun onPongReceived(data: ByteArray) {
                println("onPongReceived")
            }

            override fun onException(e: Exception) {
                println(e.message)
            }

            override fun onCloseReceived() {
                println("onCloseReceived")
            }
        }
        webSocketClient.setConnectTimeout(10000)
        webSocketClient.setReadTimeout(60000)
        webSocketClient.enableAutomaticReconnection(5000)
        webSocketClient.connect()
    }

}