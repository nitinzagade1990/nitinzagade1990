package com.example.proximitytest.applicationclass

import android.app.Application
import com.example.proximitytest.controller.SocketController

class CityApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        SocketController.createWebSocketClient(this)
    }


}