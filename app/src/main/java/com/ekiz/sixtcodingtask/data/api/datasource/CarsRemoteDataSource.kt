package com.ekiz.sixtcodingtask.data.api.datasource

import com.ekiz.sixtcodingtask.data.api.responsemodels.CarAPIModel
import com.ekiz.sixtcodingtask.data.api.services.CarsService
import javax.inject.Inject

class CarsRemoteDataSource @Inject constructor(private val service: CarsService) {

    @Throws(Exception::class)
    suspend fun getCars(): List<CarAPIModel> {
        return service.getCars()
    }

}