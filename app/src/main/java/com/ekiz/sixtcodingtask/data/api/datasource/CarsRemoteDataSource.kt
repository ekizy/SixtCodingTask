package com.ekiz.sixtcodingtask.data.api.datasource

import com.ekiz.sixtcodingtask.data.api.responsemodels.CarAPIModel
import com.ekiz.sixtcodingtask.data.api.services.CarsService
import retrofit2.Call
import javax.inject.Inject

class CarsRemoteDataSource @Inject constructor(private val service: CarsService) {

    fun getCars(): Call<List<CarAPIModel>> {
        return service.getCars()
    }

}