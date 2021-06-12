package com.ekiz.sixtcodingtask.scenes.carsoverview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekiz.sixtcodingtask.R
import com.ekiz.sixtcodingtask.base.BaseFragment
import com.ekiz.sixtcodingtask.databinding.FragmentCarsOverviewBinding


class CarsOverviewFragment : BaseFragment<CarsOverviewViewModel, FragmentCarsOverviewBinding>() {

    override fun layoutId(): Int = R.layout.fragment_cars_overview

    override fun initialize() {
        print("asdsds")
    }

    companion object {
        @JvmStatic
        fun newInstance() = CarsOverviewFragment()
    }

}