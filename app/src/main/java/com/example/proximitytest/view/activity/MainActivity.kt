package com.example.proximitytest.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proximitytest.R
import com.example.proximitytest.databinding.ActivityMainBinding
import com.example.proximitytest.view.adapter.CityAdapter
import com.example.proximitytest.viewmodel.CityViewModel
import com.example.proximitytest.viewmodel.MyViewModelFactory


class MainActivity : AppCompatActivity() {

    lateinit var model: CityViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var cityAdapter: CityAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViews()
        initViewModel()
        populateData()
    }


    fun initViews() {
        binding.idRecyclerViewCities!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    fun initViewModel() {

        var factory = MyViewModelFactory(application)
        model = ViewModelProvider(this, factory).get(CityViewModel::class.java)
    }

    private fun populateData() {

        model.getAllcityData().observe(this, {
            if (this::cityAdapter.isInitialized) {
                cityAdapter?.updateData(it)
            } else {
                cityAdapter = CityAdapter(it)
                binding.idRecyclerViewCities?.adapter = cityAdapter
            }
        })

    }


}

