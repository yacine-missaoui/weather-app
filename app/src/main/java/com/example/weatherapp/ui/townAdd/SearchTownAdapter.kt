package com.example.weatherapp.ui.townAdd


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.core.common.Utils
import com.example.weatherapp.core.domain.model.LocatedTown

class SearchTownAdapter(private val dataSet: List<LocatedTown>, private val onItemClick: (LocatedTown) -> Unit):
    RecyclerView
.Adapter<SearchTownAdapter.SearchTownViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): SearchTownViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.searched_town_item, viewGroup, false)

        return SearchTownViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: SearchTownViewHolder, position: Int) {
        val stateCountryTxt ="${dataSet[position].state}, ${Utils.getCountryNameFromCode(dataSet[position].country)}"

        viewHolder.searchedTownTextview.text = stateCountryTxt
        viewHolder.itemView.setOnClickListener {
            onItemClick(dataSet[position])
        }

    }

    override fun getItemCount() = dataSet.size

    class SearchTownViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val searchedTownTextview: TextView

        init {
            searchedTownTextview  = itemView.findViewById(R.id.searchedTownTextview)
        }
    }

}