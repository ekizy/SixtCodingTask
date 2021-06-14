package com.ekiz.sixtcodingtask.data.api.services

import com.ekiz.sixtcodingtask.data.api.responsemodels.CarAPIModel
import retrofit2.http.GET

interface CarsService {

    @Throws(Exception::class)
    @GET("cars")
    suspend fun getCars(): List<CarAPIModel>

}