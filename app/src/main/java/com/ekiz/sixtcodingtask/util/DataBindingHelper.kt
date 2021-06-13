package com.ekiz.sixtcodingtask.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ekiz.sixtcodingtask.data.uimodels.CarUIModel
import com.ekiz.sixtcodingtask.scenes.carsoverview.CarsOverviewAdapter

object DataBindingHelper {

    @JvmStatic
    @BindingAdapter("carItems", requireAll = false)
    fun setCarsRecyclerView(recyclerView: RecyclerView, items: List<CarUIModel>?) {
        items?.let { carItems ->
            recyclerView.run {
                setHasFixedSize(true)
                adapter = CarsOverviewAdapter(carItems)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                invalidateItemDecorations()
                //TODO addItemDecoration
            }
        }

    }

}