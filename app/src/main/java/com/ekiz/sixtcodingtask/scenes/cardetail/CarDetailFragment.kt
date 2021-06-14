package com.ekiz.sixtcodingtask.scenes.cardetail

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ekiz.sixtcodingtask.R
import com.ekiz.sixtcodingtask.base.BaseFragment
import com.ekiz.sixtcodingtask.data.uimodels.CarUIModel
import com.ekiz.sixtcodingtask.databinding.FragmentCarDetailBinding

class CarDetailFragment : BaseFragment<CarDetailViewModel, FragmentCarDetailBinding>() {

    private val arguments: CarDetailFragmentArgs by navArgs()

    override fun layoutId(): Int = R.layout.fragment_car_detail

    override fun initialize(savedInstanceState: Bundle?) {
        val carModel = arguments.carModel
        binder.carModel = carModel
        Glide.with(requireContext()).load(carModel.carImageUrl)
            .placeholder(R.drawable.default_car_image).into(binder.carDetailImageView)
    }


}