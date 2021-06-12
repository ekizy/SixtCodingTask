package com.ekiz.sixtcodingtask.data.api.services

import com.ekiz.sixtcodingtask.data.api.responsemodels.CarAPIModel
import retrofit2.Call
import retrofit2.http.GET

interface CarsService {

    @GET("cars")
    fun getCars(): Call<List<CarAPIModel>>

}