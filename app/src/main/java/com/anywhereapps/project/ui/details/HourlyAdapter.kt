package com.anywhereapps.project.ui.details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anywhereapps.project.R
import com.anywhereapps.project.databinding.FragmentHourlyItemBinding

import com.anywhereapps.project.network.data.Hourly


class HourlyAdapter(
    private val itemClickedListener: OnItemClickedListener,
    val mContext : Context?) : RecyclerView.Adapter<HourlyAdapter.ItemViewHolder>() {

    private var items = listOf<Hourly>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemBinding = FragmentHourlyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size


    fun submitData(items: List<Hourly>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val binding : FragmentHourlyItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item : Hourly) {
            binding.dateText.text = mContext?.getString(R.string.date, item?.dt.toString())
            binding.suriseText.text = mContext?.getString(R.string.visibility, item?.visibility.toString())
            if( item.weather.isNotEmpty()) {
                binding.sunsetText.text = mContext?.getString(R.string.weather, item.weather?.get(0).description)
            }
            binding.tempText.text = mContext?.getString(R.string.temperature, item?.temp.toString())
            binding.pressureText.text = mContext?.getString(R.string.pressure, item?.pressure.toString())
            binding.humidityText.text = mContext?.getString(R.string.humidity, item?.humidity.toString())
            binding.windspeedText.text = mContext?.getString(R.string.windspeed, item?.windSpeed.toString())

           itemView.setOnClickListener { itemClickedListener.onItemClicked(item) }
        }
    }

    interface OnItemClickedListener {
        fun onItemClicked(item: Hourly)
    }


}