package com.example.weatherapp.ui.townList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.core.common.Utils
import com.example.weatherapp.core.domain.model.LocatedTown

class LocatedTownAdapter(private val dataSet: List<LocatedTown>, private val onItemClick: (LocatedTown) -> Unit): RecyclerView
.Adapter<LocatedTownAdapter.LocatedTownViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): LocatedTownViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.located_town_item, viewGroup, false)

        return LocatedTownViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: LocatedTownViewHolder, position: Int) {
        viewHolder.townNameTextView.text = dataSet[position].name
        val stateCountryTxt ="${dataSet[position].state}, ${Utils.getCountryNameFromCode(dataSet[position].country)}"
        viewHolder.townStateCountryTextView.text = stateCountryTxt
        viewHolder.townDateTextView.text = dataSet[position].createdAt
        viewHolder.townDetailsImageButton.setOnClickListener {
            onItemClick(dataSet[position])
        }

    }

    override fun getItemCount() = dataSet.size

    class LocatedTownViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val townNameTextView: TextView
        val townStateCountryTextView: TextView
        val townDateTextView: TextView
        val townDetailsImageButton: ImageButton

        init {
            townNameTextView  = itemView.findViewById(R.id.textViewTownName)
            townStateCountryTextView  = itemView.findViewById(R.id.textViewStateCountry)
            townDateTextView  = itemView.findViewById(R.id.textViewDate)
            townDetailsImageButton  = itemView.findViewById(R.id.imageButtonTownWeatherDetails)
        }
    }

}