package com.ekiz.sixtcodingtask.domain

import com.ekiz.sixtcodingtask.data.repository.CarsRepository
import com.ekiz.sixtcodingtask.data.uimodels.CarUIModel
import com.ekiz.sixtcodingtask.util.APIResponse
import com.ekiz.sixtcodingtask.util.ErrorException
import javax.inject.Inject

class GetCarsUseCase @Inject constructor(private val carsRepository: CarsRepository) {

    suspend fun run() : APIResponse<ErrorException, List<CarUIModel>> {
        return carsRepository.getCars()
    }

}