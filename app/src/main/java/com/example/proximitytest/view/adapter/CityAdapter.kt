package com.example.proximitytest.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proximitytest.R
import com.example.proximitytest.constants.ActivityParamConstants
import com.example.proximitytest.database.citydata.City
import com.example.proximitytest.utility.DateUtility
import com.example.proximitytest.view.activity.CityWiseAQIActivity

class CityAdapter(var citilist: List<City>) : RecyclerView.Adapter<CityAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_city, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return citilist.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.newsTitle?.text = citilist.get(position).city

        holder.cityAqi?.text = String.format("%.2f", citilist.get(position).aqi)
        holder.cityAqi?.setTextColor(citilist.get(position).textColo)

        holder.lastUpdatedTime?.text =
            DateUtility.getUpdatedTimeInWord(citilist.get(position).lastModified)


        holder.itemView.setOnClickListener {

            val context = holder.newsTitle!!.context
            val intent = Intent(context, CityWiseAQIActivity::class.java)
            intent.putExtra(ActivityParamConstants.CITY_NAME, citilist.get(position).city)
            context.startActivity(intent)
        }
    }

    fun updateData(mCitilist: List<City>) {
        this.citilist = mCitilist
        notifyDataSetChanged()
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var newsTitle: TextView? = null
        var cityAqi: TextView? = null
        var lastUpdatedTime: TextView? = null


        init {
            newsTitle = itemView.findViewById(R.id.city_name);
            cityAqi = itemView.findViewById(R.id.city_aqi);
            lastUpdatedTime = itemView.findViewById(R.id.last_updated_time);
        }
    }

}