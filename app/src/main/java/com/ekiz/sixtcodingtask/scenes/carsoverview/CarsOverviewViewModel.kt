package com.ekiz.sixtcodingtask.scenes.carsoverview

import androidx.lifecycle.MutableLiveData
import com.ekiz.sixtcodingtask.base.BaseViewModel
import com.ekiz.sixtcodingtask.data.uimodels.CarUIModel
import com.ekiz.sixtcodingtask.domain.GetCarsUseCase
import com.ekiz.sixtcodingtask.util.ErrorException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CarsOverviewViewModel @Inject constructor(private val getCarsUseCase: GetCarsUseCase) : BaseViewModel() {

    var result: MutableLiveData<List<CarUIModel>> = MutableLiveData()
    var error: MutableLiveData<ErrorException> = MutableLiveData()

    fun getCars() {
        bgScope.launch {
            val carsResult = getCarsUseCase.run()
            withContext(Dispatchers.Main) {
                carsResult.decide(::handleError, ::handleSuccess)
            }
        }
    }

    private fun handleSuccess(carList: List<CarUIModel>) {
        this.result.value = carList
    }

    private fun handleError(errorException: ErrorException) {
        error.postValue(errorException)
    }

}