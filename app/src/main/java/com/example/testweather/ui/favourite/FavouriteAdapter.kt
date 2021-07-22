package com.example.testweather.ui.favourite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testweather.R
import com.example.testweather.data.entity.FavouriteForecast
import com.example.testweather.ui.favourite.FavouriteAdapter.MiniForecastHolder
import kotlinx.android.synthetic.main.item_favourite.view.*

class FavouriteAdapter(private val onLocationChange: (FavouriteForecast) -> Unit) :
    RecyclerView.Adapter<MiniForecastHolder>() {
    private val data = ArrayList<FavouriteForecast>()

    fun updateData(data: List<FavouriteForecast>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class MiniForecastHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(favouriteForecast: FavouriteForecast) = with(itemView) {
            temperature.text = favouriteForecast.temperature.toString()
            description.text = favouriteForecast.description
            locationName.text = favouriteForecast.locationName
            weatherIcon.setImageResource(favouriteForecast.imageId)
            card.setOnClickListener {
                onLocationChange(favouriteForecast)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiniForecastHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favourite, parent, false)
        return MiniForecastHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MiniForecastHolder, position: Int) {
        holder.bind(data[position])
    }
}