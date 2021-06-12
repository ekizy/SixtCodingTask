package com.ekiz.sixtcodingtask.data.repository

import com.ekiz.sixtcodingtask.data.NetworkHandler
import com.ekiz.sixtcodingtask.data.api.datasource.CarsRemoteDataSource
import com.ekiz.sixtcodingtask.data.api.responsemodels.CarAPIModel
import com.ekiz.sixtcodingtask.data.uimodels.CarUIModel
import com.ekiz.sixtcodingtask.util.ErrorException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CarsRepository @Inject constructor(private val remoteDataSource: CarsRemoteDataSource,
                                         private val networkHandler: NetworkHandler) {

    fun getCars(success: (cars: List<CarUIModel>) -> Unit,
                failure: (failure: ErrorException) -> Unit) {
        if (networkHandler.hasInternetConnection()) {
            remoteDataSource.getCars().enqueue(object :
                Callback<List<CarAPIModel>> {
                override fun onResponse(call: Call<List<CarAPIModel>>,
                                        response: Response<List<CarAPIModel>>) {
                    response.body()?.let {
                        success(it.map(CarAPIModel::toUIModel))
                    } ?: run {
                        failure(ErrorException.UnknownError)
                    }
                }

                override fun onFailure(call: Call<List<CarAPIModel>>, t: Throwable) {
                    failure(if (t is ErrorException) t else ErrorException.UnknownError)
                }

            })
        } else {
            //TODO local data source
        }
    }

}