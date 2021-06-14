package com.ekiz.sixtcodingtask.util

import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ekiz.sixtcodingtask.data.uimodels.CarUIModel
import com.ekiz.sixtcodingtask.itemdecoration.CarsOverviewItemDecoration
import com.ekiz.sixtcodingtask.scenes.carsoverview.CarsOverviewAdapter
import com.ekiz.sixtcodingtask.scenes.carsoverview.CarsOverviewFragment

object DataBindingHelper {

    @JvmStatic
    @BindingAdapter("carItems", "carItemClickListener", requireAll = false)
    fun setCarsRecyclerView(recyclerView: RecyclerView, items: List<CarUIModel>?, carItemClickListener: CarsOverviewAdapter.CarItemClickListener) {
        items?.let { carItems ->
            recyclerView.run {
                recyclerView.visibility = VISIBLE
                setHasFixedSize(true)
                adapter = CarsOverviewAdapter(carItems, carItemClickListener)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                invalidateItemDecorations()
                addItemDecoration(CarsOverviewItemDecoration(context))
                val carsOverviewFragment = recyclerView.findFragment<CarsOverviewFragment>()
                carsOverviewFragment.showMarkers(carItems)
            }
        }

    }
}