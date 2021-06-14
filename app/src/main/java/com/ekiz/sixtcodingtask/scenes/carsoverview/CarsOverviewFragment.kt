package com.ekiz.sixtcodingtask.scenes.carsoverview

import android.os.Bundle
import com.ekiz.sixtcodingtask.R
import com.ekiz.sixtcodingtask.base.BaseFragment
import com.ekiz.sixtcodingtask.data.uimodels.CarUIModel
import com.ekiz.sixtcodingtask.databinding.FragmentCarsOverviewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class CarsOverviewFragment : BaseFragment<CarsOverviewViewModel, FragmentCarsOverviewBinding>(), CarsOverviewAdapter.CarItemClickListener {


    var markerList = mutableListOf<Marker>()

    override fun layoutId(): Int = R.layout.fragment_cars_overview

    override fun initialize(savedInstanceState: Bundle?) {
        binder.viewModel = viewModel
        binder.carItemClickListener = this
    }

    override fun onCarItemClicked(adapterPosition: Int) {
        if (markerList.size > adapterPosition) {
            val marker = markerList[adapterPosition]
            marker.showInfoWindow()
            markerClicked(adapterPosition)
        }
    }

    override fun onDetailButtonClicked(carItem: CarUIModel) {
        //TODO show detail screen
    }

    fun showMarkers(carItems: List<CarUIModel>) {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { map ->
            carItems.forEach { carModel ->
                val position = LatLng(carModel.latitude ?: 0.0, carModel.longitude ?: 0.0)
                val markerOptions = MarkerOptions().position(position).title(carModel.name)
                map.addMarker(markerOptions)?.let { marker ->
                    markerList.add(marker)
                }
            }
            applyZoomToMarkers(markerList, mapFragment, map)
            map.setOnMarkerClickListener { marker ->
                val markerPosition = markerList.indexOf(marker)
                markerClicked(markerPosition)
                false
            }
        }

    }

    private fun applyZoomToMarkers(markerList: List<Marker>, mapFragment: SupportMapFragment, map: GoogleMap) {
        val builder = LatLngBounds.Builder()
        markerList.forEach { builder.include(it.position) }
        if (markerList.isNotEmpty()) {
            val bounds: LatLngBounds = builder.build()
            val width = mapFragment.view?.measuredWidth ?: 0
            val height = mapFragment.view?.measuredHeight ?: 0
            val cu =
                CameraUpdateFactory.newLatLngBounds(bounds, width, height, width / 10)
            map.animateCamera(cu)
        }
    }

    private fun markerClicked(markerPosition: Int) {
        if (markerPosition != -1) {
            val adapter = binder.recyclerView.adapter
            if (adapter is CarsOverviewAdapter) {
                adapter.selectedPosition = markerPosition
                adapter.notifyDataSetChanged()
                binder.recyclerView.smoothScrollToPosition(markerPosition)
            }
        }
    }

}