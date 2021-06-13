package com.ekiz.sixtcodingtask.scenes.carsoverview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ekiz.sixtcodingtask.R
import com.ekiz.sixtcodingtask.data.uimodels.CarUIModel
import com.ekiz.sixtcodingtask.databinding.ViewCarItemBinding

class CarsOverviewAdapter (private val carList: List<CarUIModel>) :RecyclerView.Adapter<CarsOverviewAdapter.CarViewHolder>() {

    override fun getItemCount(): Int = carList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewCarItemBinding = DataBindingUtil.inflate(inflater, R.layout.view_car_item, parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


    inner class CarViewHolder(val binding: ViewCarItemBinding) : RecyclerView.ViewHolder(binding.root)
}