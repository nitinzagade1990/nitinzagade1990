package com.example.proximitytest.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import com.example.proximitytest.R
import com.example.proximitytest.constants.ActivityParamConstants
import com.example.proximitytest.database.citydata.CityHistory
import com.example.proximitytest.viewmodel.CityHistoryViewModel
import com.example.proximitytest.viewmodel.MyViewModelFactory


class CityWiseAQIActivity : AppCompatActivity() {
    lateinit var anyChartView: AnyChartView
    lateinit var model: CityHistoryViewModel
    lateinit var cityName: String
    lateinit var liveDataHistory: LiveData<List<CityHistory>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citiwise_aqi)
        initView()
        getDataFromIntent()
        initViewModel()
        initData()


    }

    fun initView() {
        anyChartView = findViewById(R.id.any_chart_view)
        anyChartView.setProgressBar(findViewById(R.id.progress_bar))
    }

    fun getDataFromIntent() {
        cityName = intent.getStringExtra(ActivityParamConstants.CITY_NAME).toString()
    }

    fun initViewModel() {

        val factory = MyViewModelFactory(application)
        model = ViewModelProvider(this, factory).get(CityHistoryViewModel::class.java)
    }

    fun initData() {


//        model.getAllcityData(cityName)?.observe(this, {
//            val data: MutableList<DataEntry> = ArrayList()
//            val slisedList: List<CityHistory>;
//            if (it.size > 15) {
//                slisedList = it.subList(0, 15)
//            } else {
//                slisedList = it as List<CityHistory>
//            }
//
//
//            slisedList.map {
//                data.add(ValueDataEntry(it.lastModified, it.aqi))
//            }
//            updateGraphData(data)
//        })

        liveDataHistory = model.getAllcityData(cityName)

        liveDataHistory.observe(
            this,
            object : Observer<List<CityHistory>> {
                override fun onChanged(it: List<CityHistory>?) {

                    liveDataHistory.removeObserver(this)

                    val data: MutableList<DataEntry> = ArrayList()
                    var slisedList: List<CityHistory>
                    slisedList = mutableListOf()

                    if (it != null) {
                        if (it.size > 15) {
                            slisedList = it.subList(0, 15)
                        } else {
                            slisedList = it
                        }
                    }


                    slisedList.map {
                        data.add(ValueDataEntry(it.lastModified, it.aqi))
                    }
                    updateGraphData(data)
                }
            }
        )


    }


    private fun updateGraphData(data: MutableList<DataEntry>) {

        val cartesian: Cartesian = AnyChart.column()
        val column: com.anychart.core.cartesian.series.Column? = cartesian.column(data)
        if (column != null) {
            column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0.0)
                .offsetY(5.0)
                .format("")
        }

        cartesian.animation(false)
        cartesian.yScale().minimum(0.0)
        cartesian.yAxis(0).labels().format("")
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(HoverMode.BY_X)
        cartesian.xAxis(0).title("Time")
        cartesian.yAxis(0).title("AQI")

        anyChartView.setChart(cartesian)
        //anyChartView.setZoomEnabled(true)
        //  anyChartView.isHorizontalScrollBarEnabled=true

    }

}