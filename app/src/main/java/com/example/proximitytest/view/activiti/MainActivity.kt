package com.example.proximitytest

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proximitytest.database.City
import com.example.proximitytest.database.RoomSingleton
import com.google.gson.Gson
import tech.gusavila92.websocketclient.WebSocketClient
import java.net.URI
import java.net.URISyntaxException


class MainActivity : AppCompatActivity() {

    lateinit var webSocketClient: WebSocketClient
    private lateinit var mDb: RoomSingleton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDb = RoomSingleton.getInstance(applicationContext)
        createWebSocketClient()

        findViewById<Button>(R.id.btn_show).setOnClickListener {
            Toast.makeText(this ,""+  mDb.getCityDao().findByTitle().size  ,Toast.LENGTH_SHORT).show()
        }

    }

    private fun createWebSocketClient() {
        val uri: URI
        try {
            uri = URI("ws://city-ws.herokuapp.com/")
        } catch (e: URISyntaxException) {
            e.printStackTrace()
            return
        }
        webSocketClient = object : WebSocketClient(uri) {
            override fun onOpen() {
                println("onOpen")
               // webSocketClient.send("Hello, World!")

            }

            override fun onTextReceived(message: String) {
                println("onTextReceived" + message)

                val list = Gson().fromJson(message, Array<City>::class.java).toList()


                mDb.getCityDao().insert(list)

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

