package com.ekiz.sixtcodingtask.scenes.carsoverview

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.ekiz.sixtcodingtask.R
import com.ekiz.sixtcodingtask.base.BaseFragment
import com.ekiz.sixtcodingtask.databinding.FragmentCarsOverviewBinding
import com.ekiz.sixtcodingtask.util.Constants.MAPVIEW_BUNDLE_KEY
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class CarsOverviewFragment : BaseFragment<CarsOverviewViewModel, FragmentCarsOverviewBinding>(){

    override fun layoutId(): Int = R.layout.fragment_cars_overview

    override fun initialize(savedInstanceState: Bundle?) {
        binder.viewModel = viewModel
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CarsOverviewFragment()
    }

    private val callback = OnMapReadyCallback { googleMap ->
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

}