package com.ekiz.sixtcodingtask.data.repository

import com.ekiz.sixtcodingtask.data.NetworkHandler
import com.ekiz.sixtcodingtask.data.api.datasource.CarsRemoteDataSource
import com.ekiz.sixtcodingtask.data.uimodels.CarUIModel
import com.ekiz.sixtcodingtask.util.Response
import com.ekiz.sixtcodingtask.util.ErrorException
import java.lang.Exception
import javax.inject.Inject

class CarsRepository @Inject constructor(private val remoteDataSource: CarsRemoteDataSource,
                                         private val networkHandler: NetworkHandler) {

    suspend fun getCars() : Response<ErrorException, List<CarUIModel>> {
        return if (networkHandler.hasInternetConnection()) {
            try {
                Response.Success(remoteDataSource.getCars().map { it.toUIModel() })
            } catch (exception: Exception) {
                Response.Failure(ErrorException.APIError(exception.localizedMessage))
            }
        } else {
            //TODO handle local data later
            Response.Failure(ErrorException.NoConnectionError)
        }
    }
}