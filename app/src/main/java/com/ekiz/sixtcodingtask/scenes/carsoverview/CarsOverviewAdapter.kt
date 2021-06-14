package com.ekiz.sixtcodingtask.scenes.carsoverview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ekiz.sixtcodingtask.R
import com.ekiz.sixtcodingtask.data.uimodels.CarUIModel
import com.ekiz.sixtcodingtask.databinding.ViewCarItemBinding

class CarsOverviewAdapter(private val carList: List<CarUIModel>,
                          private val listener: CarItemClickListener) :
    RecyclerView.Adapter<CarsOverviewAdapter.CarViewHolder>() {

    var selectedPosition = -1

    override fun getItemCount(): Int = carList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewCarItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.view_car_item, parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]
        holder.binding.car = car
        Glide.with(holder.itemView.context).load(car.carImageUrl)
            .placeholder(R.drawable.default_car_image).into(holder.binding.carImageView)
        holder.binding.materialCardView.strokeColor =
            if (position == selectedPosition) Color.BLACK else Color.WHITE
        holder.itemView.setOnClickListener { listener.onCarItemClicked(position) }
        holder.binding.detailButton.setOnClickListener { listener.onDetailButtonClicked(car) }
        holder.binding.executePendingBindings()
    }

    inner class CarViewHolder(val binding: ViewCarItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface CarItemClickListener {
        fun onCarItemClicked(adapterPosition: Int)
        fun onDetailButtonClicked(carItem: CarUIModel)
    }
}